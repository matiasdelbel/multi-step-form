package com.matiasdelbel.multistepform.ui.pane

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

fun NavGraphBuilder.cardReviewPane(navController: NavHostController) {
    composable(CardFormRoute.Review.route) { entry ->
        val parentEntry = remember(entry) { navController.getBackStackEntry(route = CardFormRoute.CreditCardGraph) }
        val cardViewModel = hiltViewModel<CardFormViewModel>(parentEntry)
        val cardFormState by cardViewModel.state.collectAsState()

        CardReviewPane(
            cardNumber = cardFormState.maskedCardNumber,
            expiryDate = cardFormState.maskedExpiry,
            securityCode = cardFormState.cardSecurityCode.value,
            onFinish = {
                cardViewModel.onCardNumberChanged(newNumber = "")
                cardViewModel.onCardExpiryChanged(newExpiry = "")
                cardViewModel.onCardSecurityCodeChanged(newCode = "")

                navController.popBackStack(CardFormRoute.CardNumber.route, false)
            },
            onBack = { navController.popBackStack() }
        )
    }
}
