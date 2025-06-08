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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.matiasdelbel.multistepform.R
import com.matiasdelbel.multistepform.model.SecurityCodeError
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
fun SecurityPane(
    securityCode: InputFieldValue<SecurityCodeError>,
    securityCodeLength: Int,
    onSecurityCodeChanged: (String) -> Unit,
    onNext: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.padding(all = MaterialTheme.spacers.md)) {
        DecoratedTextField(
            isError = !securityCode.isValid,
            label = { TextFieldLabel(text = stringResource(R.string.security_code)) },
            textField = {
                TextField(
                    value = securityCode.value,
                    onValueChange = { newValue ->
                        onSecurityCodeChanged(newValue.filter { it.isDigit() }.take(securityCodeLength))
                    },
                    placeholder = { TextFieldHint(text = stringResource(R.string.security_code_hint)) },
                    isError = !securityCode.isValid,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            error = {
                val error = securityCode.error!!
                val errorText = when (error) {
                    SecurityCodeError.Empty -> stringResource(R.string.security_code_empty_error)
                    SecurityCodeError.InvalidLength -> stringResource(R.string.security_code_invalid_length_error)
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
                    enabled = securityCode.isValid,
                )
            }
        )
    }
}

@Preview
@Composable
private fun SecurityPanePreview() {
    AppTheme {
        Column {
            SecurityPane(
                securityCode = InputFieldValue(value = "123"),
                securityCodeLength = 3,
                onSecurityCodeChanged = {},
                onNext = {},
                onBack = {}
            )

            SecurityPane(
                securityCode = InputFieldValue(
                    value = "",
                    error = SecurityCodeError.Empty
                ),
                securityCodeLength = 3,
                onSecurityCodeChanged = {},
                onNext = {},
                onBack = {}
            )

            SecurityPane(
                securityCode = InputFieldValue(
                    value = "123",
                    error = SecurityCodeError.InvalidLength
                ),
                securityCodeLength = 4,
                onSecurityCodeChanged = {},
                onNext = {},
                onBack = {}
            )
        }
    }
}
