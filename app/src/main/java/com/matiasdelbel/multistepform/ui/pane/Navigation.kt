// Simplificado: estructura base + primer paso funcional

// 1. Navigation.kt
package com.matiasdelbel.multistepform.ui.pane

sealed class Screen(val route: String) {
    object Step1 : Screen("step1")
    object Step2 : Screen("step2")
    object Step3 : Screen("step3")
    object Result : Screen("result")
}
