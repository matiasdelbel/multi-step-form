package com.matiasdelbel.multistepform.model.validation

import com.matiasdelbel.multistepform.model.CardNumberError
import javax.inject.Inject

class CardNumberValidator @Inject constructor() {

    fun validate(number: String): CardNumberError? = when {
        number.isBlank() -> CardNumberError.Empty
        !isValidLuhn(number) -> CardNumberError.InvalidLuhn
        else -> null
    }

    private fun isValidLuhn(cardNumber: String): Boolean {
        val digits = cardNumber.filter { it.isDigit() }
        if (digits.length < 13) return false

        val sum = digits
            .reversed()
            .mapIndexed { index, char ->
                val digit = char.code
                if (index % 2 == 1) {
                    val dbl = digit * 2
                    if (dbl > 9) dbl - 9 else dbl
                } else {
                    digit
                }
            }
            .sum()

        return sum % 10 == 0
    }
}
