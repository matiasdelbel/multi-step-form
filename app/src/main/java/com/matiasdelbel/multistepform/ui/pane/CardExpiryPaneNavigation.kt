package com.matiasdelbel.multistepform.ui.pane

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

fun NavGraphBuilder.cardExpiryPane(navController: NavHostController) {
    composable(route = CardFormRoute.CardExpiry.route) { entry ->
        val parentEntry = remember(entry) { navController.getBackStackEntry(route = CardFormRoute.CreditCardGraph) }
        val cardViewModel = hiltViewModel<CardFormViewModel>(parentEntry)
        val cardFormState by cardViewModel.state.collectAsState()

        CardExpiryPane(
            expiry = cardFormState.cardExpiry,
            onExpiryChanged = cardViewModel::onCardExpiryChanged,
            onNext = { navController.navigate(route = CardFormRoute.SecurityCode.route) },
            onBack = { navController.popBackStack() }
        )
    }
}
