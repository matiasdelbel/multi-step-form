package com.matiasdelbel.multistepform.model

sealed class CardNumberError {
    object Empty : CardNumberError()
    object InvalidLuhn : CardNumberError()
}

sealed class ExpiryDateError {
    object Empty : ExpiryDateError()
    object Expired : ExpiryDateError()
    object InvalidMonth : ExpiryDateError()
    object Malformed : ExpiryDateError()
}

sealed class SecurityCodeError {
    object Empty : SecurityCodeError()
    object InvalidLength : SecurityCodeError()
}
