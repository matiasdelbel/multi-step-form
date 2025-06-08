package com.matiasdelbel.multistepform.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.matiasdelbel.multistepform.ui.theme.AppTheme

@Composable
fun Button(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
    ) {
        Text(text = text)
    }
}

@Composable
fun TextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    TextButton(onClick = onClick,
        modifier = modifier,
        enabled = enabled,
    ) {
        Text(text = text)
    }
}

@Preview
@Composable
private fun ButtonPreview() {
    AppTheme {
        Column {
            Button(text = "Button", onClick = { })
            TextButton(text = "Button", onClick = { })
        }
    }
}
