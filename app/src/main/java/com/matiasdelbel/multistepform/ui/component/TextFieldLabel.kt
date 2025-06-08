package com.matiasdelbel.multistepform.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.matiasdelbel.multistepform.ui.theme.AppTheme

@Composable
fun TextFieldLabel(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier
    )
}

@Preview
@Composable
private fun TextFieldPreview() {
    AppTheme {
        TextFieldLabel(text = "TextField label")
    }
}
