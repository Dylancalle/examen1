package com.calyrsoft.ucbp1.features.profile.domain.vo

// Importar la excepción definida en Name.kt
import com.calyrsoft.ucbp1.features.profile.domain.vo.ProfileValidationException.InvalidEmail

// RegEx simple para validar estructura básica de email
private val EMAIL_REGEX = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$".toRegex()

data class Email(val value: String) {
    init {
        // Validación: Verifica el formato del correo electrónico
        require(value.matches(EMAIL_REGEX)) {
            throw InvalidEmail(value)
        }
    }
}