package com.matiasdelbel.multistepform.ui.pane

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Step1Pane(
    formState: FormState,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onNext: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Step 1: Personal Info")
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = formState.name,
            onValueChange = onNameChanged,
            label = { Text("Name") })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = formState.email,
            onValueChange = onEmailChanged,
            label = { Text("Email") })
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNext, modifier = Modifier.align(Alignment.End)) { Text("Next") }
    }
}

@Preview
@Composable
private fun Step1PanePreview() {
    Step1Pane(
        formState = FormState(
            name = "John Doe",
        ),
        onNameChanged = { },
        onEmailChanged = { },
        onNext = { },
    )
}
