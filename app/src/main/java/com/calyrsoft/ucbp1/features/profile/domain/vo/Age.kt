package com.calyrsoft.ucbp1.features.profile.domain.vo

// Importar la excepción definida en Name.kt
import com.calyrsoft.ucbp1.features.profile.domain.vo.ProfileValidationException.InvalidAge

data class Age(val value: Int) {
    init {
        // Validación: La edad debe estar dentro de un rango razonable
        require(value >= 18 && value <= 100) {
            throw InvalidAge(value)
        }
    }
}