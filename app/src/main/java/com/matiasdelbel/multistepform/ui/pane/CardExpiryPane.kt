package com.matiasdelbel.multistepform.ui.pane

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
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
import com.matiasdelbel.multistepform.model.ExpiryDateError
import com.matiasdelbel.multistepform.ui.component.Button
import com.matiasdelbel.multistepform.ui.component.NavButtons
import com.matiasdelbel.multistepform.ui.component.TextFieldError
import com.matiasdelbel.multistepform.ui.component.TextFieldHint
import com.matiasdelbel.multistepform.ui.component.TextFieldLabel
import com.matiasdelbel.multistepform.ui.component.DecoratedTextField
import com.matiasdelbel.multistepform.ui.component.TextButton
import com.matiasdelbel.multistepform.ui.theme.AppTheme
import com.matiasdelbel.multistepform.ui.theme.spacers

@Composable
fun CardExpiryPane(
    expiry: InputFieldValue<ExpiryDateError>,
    onExpiryChanged: (String) -> Unit,
    onNext: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.padding(all = MaterialTheme.spacers.md)) {
        DecoratedTextField(
            isError = !expiry.isValid,
            label = { TextFieldLabel(text = stringResource(R.string.expiry_date)) },
            textField = {
                TextField(
                    value = expiry.value,
                    onValueChange = { newValue ->
                        onExpiryChanged(newValue.filter { it.isDigit() }.take(4))
                    },
                    placeholder = { TextFieldHint(text = stringResource(R.string.expiry_date_format_hint)) },
                    isError = !expiry.isValid,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = ExpiryDateVisualTransformation()
                )
            },
            error = {
                val error = expiry.error!!
                val errorText = when (error) {
                    ExpiryDateError.Empty -> stringResource(R.string.expiry_date_empty_error)
                    ExpiryDateError.Expired -> stringResource(R.string.expiry_date_expired_error)
                    ExpiryDateError.InvalidMonth -> stringResource(R.string.expiry_date_invalid_month)
                    ExpiryDateError.Malformed -> stringResource(R.string.expiry_date_invalid_format)
                }

                TextFieldError(text = errorText)
            },
        )
        Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.sm))
        NavButtons(
            back = {
                TextButton(
                    text = stringResource(R.string.card_form_back),
                    onClick = onBack
                )
            },
            next = {
                Button(
                    text = stringResource(R.string.card_form_next),
                    onClick = onNext,
                    enabled = expiry.isValid,
                )
            }
        )
    }
}


private class ExpiryDateVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text.filter { it.isDigit() }.take(4)

        val formatted = buildString {
            for (i in digits.indices) {
                append(digits[i])
                if (i == 1 && digits.length > 2) {
                    append("/")
                }
            }
        }

        val offsetMapping = ExpiryDateOffsetMapping(digits)
        return TransformedText(AnnotatedString(formatted), offsetMapping)
    }

    private class ExpiryDateOffsetMapping(private val digits: String) : OffsetMapping {

        override fun originalToTransformed(offset: Int): Int {
            val cleanOffset = offset.coerceIn(0, digits.length)
            return when {
                cleanOffset <= 2 -> cleanOffset
                cleanOffset in 3..4 -> cleanOffset + 1
                else -> digits.length + if (digits.length > 2) 1 else 0
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return when {
                offset <= 2 -> offset
                offset in 3..5 -> offset - 1
                else -> digits.length
            }
        }
    }
}

@Preview
@Composable
private fun CardExpiryPanePreview() {
    AppTheme {
        Column {
            CardExpiryPane(
                expiry = InputFieldValue(value = "12/25"),
                onExpiryChanged = {},
                onNext = {},
                onBack = {}
            )
            CardExpiryPane(
                expiry = InputFieldValue(
                    value = "",
                    error = ExpiryDateError.Empty
                ),
                onExpiryChanged = {},
                onNext = {},
                onBack = {}
            )
            CardExpiryPane(
                expiry = InputFieldValue(
                    value = "12-25",
                    error = ExpiryDateError.Malformed
                ),
                onExpiryChanged = {},
                onNext = {},
                onBack = {}
            )
            CardExpiryPane(
                expiry = InputFieldValue(
                    value = "12/20",
                    error = ExpiryDateError.Expired
                ),
                onExpiryChanged = {},
                onNext = {},
                onBack = {}
            )
        }
    }
}
