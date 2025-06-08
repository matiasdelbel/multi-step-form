package com.matiasdelbel.multistepform.ui.pane

import androidx.lifecycle.ViewModel
import com.matiasdelbel.multistepform.model.validation.CardNumberValidator
import com.matiasdelbel.multistepform.model.CardType
import com.matiasdelbel.multistepform.model.validation.ExpiryDateValidator
import com.matiasdelbel.multistepform.model.validation.SecurityCodeValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CardFormViewModel @Inject constructor(
    private val cardNumberValidator: CardNumberValidator,
    private val expiryDateValidator: ExpiryDateValidator,
    private val securityCodeValidator: SecurityCodeValidator,
) : ViewModel() {

    private val _state = MutableStateFlow(value = CardFormState())
    val state: StateFlow<CardFormState> = _state

    fun onCardNumberChanged(newNumber: String) {
        _state.value = _state.value.copy(
            cardNumber = InputFieldValue(
                value = newNumber,
                error = cardNumberValidator.validate(newNumber)
            ),
            cardType = detectCardType(newNumber)
        )
    }

    fun onCardExpiryChanged(newExpiry: String) {
        _state.value = _state.value.copy(
            cardExpiry = InputFieldValue(
                value = newExpiry,
                error = expiryDateValidator.validate(expiry = newExpiry),
            )
        )
    }

    fun onCardSecurityCodeChanged(newCode: String) {
        _state.value = _state.value.copy(
            cardSecurityCode = InputFieldValue(
                value = newCode,
                error = securityCodeValidator.validate(
                    code = newCode,
                    expectedLength = _state.value.cardType.ccvLength
                )
            )
        )
    }

    private fun detectCardType(cardNumber: String): CardType {
        val digits = cardNumber.filter { it.isDigit() }
        return CardType.entries.firstOrNull { it.pattern.matches(digits) } ?: CardType.Unknown
    }
}
