package com.matiasdelbel.multistepform.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.matiasdelbel.multistepform.ui.theme.AppTheme
import com.matiasdelbel.multistepform.ui.theme.spacers

@Composable
fun TextFieldError(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.labelSmall,
        modifier = modifier.padding(top = MaterialTheme.spacers.xs)
    )
}

@Preview
@Composable
private fun TextFieldErrorPreview() {
    AppTheme {
        TextFieldError(text = "TextField error")
    }
}
