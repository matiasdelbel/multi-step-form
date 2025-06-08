package com.matiasdelbel.multistepform.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.matiasdelbel.multistepform.ui.theme.AppTheme
import com.matiasdelbel.multistepform.ui.theme.spacers

@Composable
fun NavButtons(
    next: @Composable RowScope.() -> Unit,
    back: (@Composable RowScope.() -> Unit)?,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = modifier.fillMaxWidth(),
    ) {
        back?.let { it() }
        Spacer(modifier = Modifier.width(width = MaterialTheme.spacers.sm))
        next()
    }
}

@Preview
@Composable
private fun NavButtonsPreview() {
    AppTheme {
        NavButtons(
            next = { Button(text = "Next", onClick = {}) },
            back = { TextButton(text = "Back", onClick = {}) }
        )
    }
}
