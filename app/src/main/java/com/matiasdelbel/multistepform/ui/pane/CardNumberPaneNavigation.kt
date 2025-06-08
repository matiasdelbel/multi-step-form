package com.matiasdelbel.multistepform.ui.pane

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

fun NavGraphBuilder.cardNumberPane(navController: NavHostController) {
    composable(route = CardFormRoute.CardNumber.route) { entry ->
        val parentEntry = remember(entry) { navController.getBackStackEntry(route = CardFormRoute.CreditCardGraph) }
        val cardViewModel = hiltViewModel<CardFormViewModel>(parentEntry)
        val cardFormState by cardViewModel.state.collectAsState()

        CardNumberPane(
            number = cardFormState.cardNumber,
            mask = cardFormState.cardType.mask,
            onCardNumberChange = cardViewModel::onCardNumberChanged,
            onNext = { navController.navigate(route = CardFormRoute.CardExpiry.route) }
        )
    }
}