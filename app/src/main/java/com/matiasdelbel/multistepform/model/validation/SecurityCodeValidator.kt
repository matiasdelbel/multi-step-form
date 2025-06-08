package com.matiasdelbel.multistepform.model.validation

import com.matiasdelbel.multistepform.model.SecurityCodeError
import javax.inject.Inject

class SecurityCodeValidator @Inject constructor() {

    fun validate(code: String, expectedLength: Int): SecurityCodeError? = when {
        code.isBlank() -> SecurityCodeError.Empty
        code.length != expectedLength -> SecurityCodeError.InvalidLength
        else -> null
    }
}
