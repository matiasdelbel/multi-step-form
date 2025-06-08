package com.matiasdelbel.multistepform.ui.pane

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

fun NavGraphBuilder.cardSecurityPane(navController: NavHostController) {
    composable(CardFormRoute.SecurityCode.route) { entry ->
        val parentEntry = remember(entry) { navController.getBackStackEntry(route = CardFormRoute.CreditCardGraph) }
        val cardViewModel = hiltViewModel<CardFormViewModel>(parentEntry)
        val cardFormState by cardViewModel.state.collectAsState()

        SecurityPane(
            securityCode = cardFormState.cardSecurityCode,
            securityCodeLength = cardFormState.cardType.ccvLength,
            onSecurityCodeChanged = cardViewModel::onCardSecurityCodeChanged,
            onNext = { navController.navigate(route = CardFormRoute.Review.route) },
            onBack = { navController.popBackStack() },
        )
    }
}
