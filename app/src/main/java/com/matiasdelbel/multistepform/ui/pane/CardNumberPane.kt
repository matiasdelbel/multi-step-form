package com.matiasdelbel.multistepform.ui.pane

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.matiasdelbel.multistepform.R
import com.matiasdelbel.multistepform.model.CardNumberError
import com.matiasdelbel.multistepform.ui.component.Button
import com.matiasdelbel.multistepform.ui.component.NavButtons
import com.matiasdelbel.multistepform.ui.component.TextFieldError
import com.matiasdelbel.multistepform.ui.component.TextFieldHint
import com.matiasdelbel.multistepform.ui.component.TextFieldLabel
import com.matiasdelbel.multistepform.ui.component.DecoratedTextField
import com.matiasdelbel.multistepform.ui.component.TextFieldHelperText
import com.matiasdelbel.multistepform.ui.theme.AppTheme
import com.matiasdelbel.multistepform.ui.theme.spacers

@Composable
fun CardNumberPane(
    number: InputFieldValue<CardNumberError>,
    mask: String,
    onCardNumberChange: (newNumber: String) -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier.padding(all = MaterialTheme.spacers.md)) {
        DecoratedTextField(
            isError = !number.isValid,
            label = { TextFieldLabel(text = stringResource(R.string.card_number)) },
            textField = {
                TextField(
                    value = number.value,
                    onValueChange = { newValue ->
                        onCardNumberChange(newValue.filter { it.isDigit() }.take(mask.count({ it == '#'})))
                    },
                    placeholder = { TextFieldHint(text = stringResource(R.string.card_number_hint)) },
                    isError = !number.isValid,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = CardNumberVisualTransformation(mask)
                )
            },
            helperText = { TextFieldHelperText(text = stringResource(R.string.card_number_helper)) },
            error = {
                val error = number.error!!
                val errorText = when (error) {
                    CardNumberError.Empty -> stringResource(R.string.card_number_empty_error)
                    CardNumberError.InvalidLuhn -> stringResource(R.string.card_number_invalid_error)
                }

                TextFieldError(text = errorText)
            },
        )
        Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.sm))
        NavButtons(
            back = null,
            next = {
                Button(
                    text = stringResource(R.string.card_form_next),
                    onClick = onNext,
                    enabled = number.isValid,
                )
            }
        )
    }
}

class CardNumberVisualTransformation(private val mask: String) : VisualTransformation {

    private val placeholderCount = mask.count { it == '#' }

    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text.filter { it.isDigit() }.take(placeholderCount)

        val formatted = buildString {
            var digitIndex = 0
            for (c in mask) {
                if (c == '#') {
                    if (digitIndex < digits.length) {
                        append(digits[digitIndex])
                        digitIndex++
                    } else {
                        break
                    }
                } else {
                    append(c)
                }
            }
        }

        val offsetMapping = CardNumberOffsetMapping(mask, digits)
        return TransformedText(AnnotatedString(formatted), offsetMapping)
    }

    private class CardNumberOffsetMapping(
        private val mask: String,
        private val digits: String
    ) : OffsetMapping {

        private val maskCharIndexes = mask.withIndex()
            .filter { it.value == '#' }
            .map { it.index }

        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 0) return 0
            val clampedOffset = offset.coerceAtMost(digits.length)

            return if (clampedOffset == 0) {
                0
            } else {
                (maskCharIndexes.getOrElse(clampedOffset - 1) { mask.length } + 1)
                    .coerceAtMost(mask.length)
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 0) return 0

            val firstMaskCharIndex = maskCharIndexes.firstOrNull() ?: 0

            if (offset <= firstMaskCharIndex) {
                return 0
            }

            val rawIndex = maskCharIndexes.count { it < offset }
            return rawIndex.coerceAtMost(digits.length)
        }
    }
}

@Preview
@Composable
private fun CardNumberPanePreview() {
    AppTheme {
        Column {
            CardNumberPane(
                number = InputFieldValue(value = "4350 4034 2332 3443"),
                mask = "#### #### #### ####",
                onCardNumberChange = { },
                onNext = { },
            )
            CardNumberPane(
                number = InputFieldValue(
                    value = "",
                    error = CardNumberError.Empty
                ),
                mask = "#### #### #### ####",
                onCardNumberChange = { },
                onNext = { },
            )
            CardNumberPane(
                number = InputFieldValue(
                    value = "4350 4034 2332 3443",
                    error = CardNumberError.InvalidLuhn
                ),
                mask = "#### #### #### ####",
                onCardNumberChange = { },
                onNext = { },
            )
        }
    }
}
