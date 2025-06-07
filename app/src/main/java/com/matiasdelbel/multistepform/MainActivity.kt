package com.matiasdelbel.multistepform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.SavedStateViewModelFactory
import com.matiasdelbel.multistepform.ui.pane.FormViewModel
import com.matiasdelbel.multistepform.ui.pane.ResultPane
import com.matiasdelbel.multistepform.ui.pane.Step1Pane
import com.matiasdelbel.multistepform.ui.pane.Step2Pane
import com.matiasdelbel.multistepform.ui.pane.Step3Pane
import com.matiasdelbel.multistepform.ui.theme.MultiStepFormTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MultiStepFormTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
                val navController = rememberNavController()
                val viewModel: FormViewModel = viewModel(
                    factory = SavedStateViewModelFactory(application, this)
                )

                NavHost(navController, startDestination = "step1") {
                    composable("step1") {
                        Step1Pane(
                            formState = viewModel.formState.collectAsState().value,
                            onNameChanged = viewModel::onNameChanged,
                            onEmailChanged = viewModel::onEmailChanged,
                            onNext = {
                                if (viewModel.validateStep1()) navController.navigate("step2")
                            }
                        )
                    }
                    composable("step2") {
                        Step2Pane(
                            formState = viewModel.formState.collectAsState().value,
                            onOptionSelected = viewModel::onOptionChanged,
                            onNext = {
                                if (viewModel.validateStep2()) navController.navigate("step3")
                            },
                            onBack = { navController.popBackStack() }
                        )
                    }
                    composable("step3") {
                        Step3Pane(
                            formState = viewModel.formState.collectAsState().value,
                            onCommentChanged = viewModel::onCommentChanged,
                            onNext = {
                                navController.navigate("result")
                            },
                            onBack = { navController.popBackStack() }
                        )
                    }
                    composable("result") {
                        ResultPane(
                            formState = viewModel.formState.collectAsState().value,
                            onReset = {
                                viewModel.reset()
                                navController.navigate("step1") {
                                    popUpTo("step1") { inclusive = true }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    MultiStepFormTheme {
        Greeting("Android")
    }
}