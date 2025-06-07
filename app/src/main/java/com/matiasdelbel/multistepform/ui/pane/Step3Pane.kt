package com.matiasdelbel.multistepform.ui.pane

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Step3Pane(
    formState: FormState,
    onCommentChanged: (String) -> Unit,
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Step 3: Additional Comments")
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = formState.comment,
            onValueChange = onCommentChanged,
            label = { Text("Comment") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            OutlinedButton(onClick = onBack) { Text("Back") }
            Button(onClick = onNext) { Text("Finish") }
        }
    }
}

@Preview
@Composable
private fun Step3PanePreview() {
    Step3Pane(
        formState = FormState(
            name = "John Doe",
        ),
        onCommentChanged = { },
        onNext = { },
        onBack = { }
    )
}