package com.matiasdelbel.multistepform.ui.pane

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation

fun NavGraphBuilder.cardForm(navController: NavHostController) {
    navigation(
        route = CardFormRoute.CreditCardGraph,
        startDestination = CardFormRoute.CardNumber.route
    ) {
        cardNumberPane(navController = navController)
        cardExpiryPane(navController = navController)
        cardSecurityPane(navController = navController)
        cardReviewPane(navController = navController)
    }
}

sealed class CardFormRoute(val route: String) {
    object CardNumber : CardFormRoute(route = "card_number")
    object CardExpiry : CardFormRoute(route = "card_expiry")
    object Review : CardFormRoute(route = "card_review")
    object SecurityCode : CardFormRoute(route = "card_security")

    companion object {
        const val CreditCardGraph = "card_graph"
    }
}
