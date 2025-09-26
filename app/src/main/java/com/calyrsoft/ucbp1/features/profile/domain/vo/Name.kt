package com.calyrsoft.ucbp1.features.profile.domain.vo

// Excepción de dominio para manejar errores de validación
sealed class ProfileValidationException(message: String) : Exception(message) {
    class InvalidName(name: String) : ProfileValidationException("El nombre '$name' debe tener entre 3 y 50 caracteres y solo letras.")
    class InvalidEmail(email: String) : ProfileValidationException("El formato del email '$email' es inválido.")
    class InvalidAge(age: Int) : ProfileValidationException("La edad $age debe estar entre 18 y 100 años.")
}

data class Name(val value: String) {
    init {
        // Validación: Longitud entre 3 y 50 caracteres y solo contiene letras/espacios
        require(value.isNotBlank() && value.length >= 3 && value.length <= 50) {
            throw ProfileValidationException.InvalidName(value)
        }
        require(value.all { it.isLetter() || it.isWhitespace() }) {
            throw ProfileValidationException.InvalidName(value)
        }
    }
}