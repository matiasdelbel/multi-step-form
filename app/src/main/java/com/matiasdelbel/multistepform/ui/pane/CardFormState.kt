package com.matiasdelbel.multistepform.ui.pane

import com.matiasdelbel.multistepform.model.CardNumberError
import com.matiasdelbel.multistepform.model.CardType
import com.matiasdelbel.multistepform.model.ExpiryDateError
import com.matiasdelbel.multistepform.model.SecurityCodeError

/**
 * @property cardExpiry - expiry date in the format "MMYY".
 */
data class CardFormState(
    val cardNumber: InputFieldValue<CardNumberError> = InputFieldValue(),
    val cardExpiry: InputFieldValue<ExpiryDateError> = InputFieldValue(),
    val cardSecurityCode: InputFieldValue<SecurityCodeError> = InputFieldValue(),
    val cardType: CardType = CardType.Unknown
)

data class InputFieldValue<E : Any>(
    val value: String = "",
    val error: E? = null
) {
    val isValid: Boolean get() = error == null
}

val CardFormState.maskedCardNumber
    get() = cardNumber
        .value
        .takeLast(4)
        .padStart(cardNumber.value.length, '*')
        .chunked(4)
        .joinToString(" ")

val CardFormState.maskedExpiry: String
    get() {
        val digits = cardExpiry.value.filter { it.isDigit() }.take(4)

        return when (digits.length) {
            0 -> ""
            1, 2 -> digits
            in 3..4 -> "${digits.substring(0, 2)}/${digits.substring(2)}"
            else -> ""
        }
    }