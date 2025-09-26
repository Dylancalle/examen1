package com.calyrsoft.ucbp1.features.profile.domain.model

// Importamos los Value Objects creados
import com.calyrsoft.ucbp1.features.profile.domain.vo.Email
import com.calyrsoft.ucbp1.features.profile.domain.vo.Name
import com.calyrsoft.ucbp1.features.profile.domain.vo.Age

data class ProfileModel(
    val name: Name,
    val email: Email,
    val age: Age,
    val isVerified: Boolean = false
)