package com.matiasdelbel.multistepform.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.matiasdelbel.multistepform.R
import com.matiasdelbel.multistepform.ui.theme.AppTheme
import com.matiasdelbel.multistepform.ui.theme.spacers

@Composable
fun CreditCard(
    cardNumber: String,
    expiryDate: String,
    securityCode: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .aspectRatio(1.585f)
            .clip(shape = RoundedCornerShape(size = MaterialTheme.spacers.md))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.secondary
                    )
                )
            )
            .padding(all = MaterialTheme.spacers.md),
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = stringResource(R.string.card_number),
            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
        )
        Text(
            text = cardNumber,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.headlineSmall,
            letterSpacing = 2.sp
        )
        Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.md))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    text = stringResource(R.string.expiry_date),
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                )
                Text(
                    text = expiryDate,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            Column {
                Text(
                    text = stringResource(R.string.security_code_cvv),
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                )
                Text(
                    text = securityCode,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Preview
@Composable
fun CreditCardPreview() {
    AppTheme {
        CreditCard(
            cardNumber = "**** **** **** 3443",
            expiryDate = "12/25",
            securityCode = "123",
        )
    }
}
