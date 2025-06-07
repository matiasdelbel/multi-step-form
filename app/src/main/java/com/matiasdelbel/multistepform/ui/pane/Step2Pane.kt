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
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Step2Pane(
    formState: FormState,
    onOptionSelected: (String) -> Unit,
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Step 2: Choose Option")
        Spacer(modifier = Modifier.height(8.dp))
        RadioButtonWithLabel("Option A", formState.option == "A") { onOptionSelected("A") }
        RadioButtonWithLabel("Option B", formState.option == "B") { onOptionSelected("B") }
        Spacer(modifier = Modifier.height(16.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            OutlinedButton(onClick = onBack) { Text("Back") }
            Button(onClick = onNext) { Text("Next") }
        }
    }
}

@Composable
private fun RadioButtonWithLabel(label: String, selected: Boolean, onSelect: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = selected, onClick = onSelect)
        Text(label)
    }
}

@Preview
@Composable
private fun Step2ScreenPreview() {
    Step2Pane(
        formState = FormState(
            name = "John Doe",
        ),
        onOptionSelected = { },
        onNext = { },
        onBack = { }
    )
}