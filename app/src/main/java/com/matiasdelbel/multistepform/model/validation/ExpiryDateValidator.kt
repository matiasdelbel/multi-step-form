package com.matiasdelbel.multistepform.model.validation

import com.matiasdelbel.multistepform.model.ExpiryDateError
import java.time.LocalDate
import java.time.Year
import javax.inject.Inject

class ExpiryDateValidator @Inject constructor() {

    /**
     *  Validates expiry date in the format "MMYY".
     */
    fun validate(expiry: String): ExpiryDateError? {
        if (expiry.isBlank()) return ExpiryDateError.Empty
        if (expiry.length != 4) return ExpiryDateError.Malformed

        val month = expiry.substring(0, 2).toIntOrNull() ?: return ExpiryDateError.Malformed
        val year = expiry.substring(2, 4).toIntOrNull() ?: return ExpiryDateError.Malformed

        if (month !in 1..12) {
            return  ExpiryDateError.InvalidMonth
        }

        val currentYear = Year.now().value % 100
        val currentMonth = LocalDate.now().monthValue
        if (year < currentYear || year == currentYear && month < currentMonth) return ExpiryDateError.Expired

        return null
    }
}
