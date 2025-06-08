package com.matiasdelbel.multistepform.ui.pane

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.matiasdelbel.multistepform.R
import com.matiasdelbel.multistepform.ui.component.Button
import com.matiasdelbel.multistepform.ui.component.CreditCard
import com.matiasdelbel.multistepform.ui.component.NavButtons
import com.matiasdelbel.multistepform.ui.component.TextButton
import com.matiasdelbel.multistepform.ui.theme.AppTheme
import com.matiasdelbel.multistepform.ui.theme.spacers

@Composable
fun CardReviewPane(
    cardNumber: String,
    expiryDate: String,
    securityCode: String,
    onFinish: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(all = MaterialTheme.spacers.md)) {
        CreditCard(
            cardNumber = cardNumber,
            expiryDate = expiryDate,
            securityCode = securityCode,
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
                    text = stringResource(R.string.card_form_finish),
                    onClick = onFinish,
                )
            }
        )
    }
}

@Preview
@Composable
private fun CardReviewPanePreview() {
    AppTheme {
        CardReviewPane(
            cardNumber = "**** **** **** 3443",
            expiryDate = "12/25",
            securityCode = "123",
            onFinish = {},
            onBack = {},
        )
    }
}
