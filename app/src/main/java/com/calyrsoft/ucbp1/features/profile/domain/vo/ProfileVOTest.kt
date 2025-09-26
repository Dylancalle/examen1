package com.calyrsoft.ucbp1.features.profile.domain.vo

import org.junit.Assert.*
import org.junit.Test

class ProfileVOTest {

    // --- Pruebas para Name ---
    @Test
    fun `Name creation is successful with valid data`() {
        val validName = "Juan Perez"
        val nameVO = Name(validName)
        assertEquals(validName, nameVO.value)
    }

    @Test(expected = ProfileValidationException.InvalidName::class)
    fun `Name creation fails when name is too short`() {
        Name("Yo") // Menos de 3 caracteres
    }

    @Test(expected = ProfileValidationException.InvalidName::class)
    fun `Name creation fails when name contains invalid characters`() {
        Name("Juan123") // Contiene números
    }

    // --- Pruebas para Email ---
    @Test
    fun `Email creation is successful with valid format`() {
        val validEmail = "test.user@domain.com"
        val emailVO = Email(validEmail)
        assertEquals(validEmail, emailVO.value)
    }

    @Test(expected = ProfileValidationException.InvalidEmail::class)
    fun `Email creation fails without at symbol`() {
        Email("invalid.email.com")
    }

    @Test(expected = ProfileValidationException.InvalidEmail::class)
    fun `Email creation fails with wrong domain format`() {
        Email("user@domain")
    }

    // --- Pruebas para Age ---
    @Test
    fun `Age creation is successful with valid age`() {
        val validAge = 35
        val ageVO = Age(validAge)
        assertEquals(validAge, ageVO.value)
    }

    @Test(expected = ProfileValidationException.InvalidAge::class)
    fun `Age creation fails when age is below minimum (18)`() {
        Age(17)
    }

    @Test(expected = ProfileValidationException.InvalidAge::class)
    fun `Age creation fails when age is above maximum (100)`() {
        Age(101)
    }
}