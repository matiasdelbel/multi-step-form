package com.matiasdelbel.multistepform.model

sealed class CardType(
    val pattern: Regex,
    val mask: String,
    val ccvLength: Int
) {

    object Amex : CardType(
        pattern = Regex("^3[47]\\d*"),
        mask = "#### ###### #####",
        ccvLength = 4
    )

    object Discover : CardType(
        pattern = Regex("^6(?:011|5)\\d*"),
        mask = "#### #### #### ####",
        ccvLength = 3
    )

    object Mastercard : CardType(
        pattern = Regex("^5[1-5]\\d*"),
        mask = "#### #### #### ####",
        ccvLength = 3
    )
    object Visa : CardType(
        pattern = Regex("^4\\d*"),
        mask = "#### #### #### ####",
        ccvLength = 3
    )

    object Unknown : CardType(
        pattern = Regex(".*"),
        mask = "#### #### #### ####",
        ccvLength = 3
    )

    companion object {
        val entries = listOf(Amex, Discover, Mastercard, Visa, Unknown)
    }
}
