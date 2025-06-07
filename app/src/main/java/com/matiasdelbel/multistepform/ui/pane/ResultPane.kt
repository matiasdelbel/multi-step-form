package com.matiasdelbel.multistepform.ui.pane

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ResultPane(
    formState: FormState,
    onReset: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Result Summary")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Name: ${formState.name}")
        Text("Email: ${formState.email}")
        Text("Option: ${formState.option}")
        Text("Comment: ${formState.comment}")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onReset) { Text("Start Over") }
    }
}

@Preview
@Composable
private fun ResultPanePreview() {
    ResultPane(
        formState = FormState(
            name = "John Doe",
        ),
        onReset = { }
    )
}