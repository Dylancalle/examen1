package com.calyrsoft.ucbp1.features.profile.domain.usecase

import com.calyrsoft.ucbp1.features.profile.domain.model.ProfileModel
import com.calyrsoft.ucbp1.features.profile.domain.repository.ProfileRepository
import com.calyrsoft.ucbp1.features.profile.domain.vo.Age
import com.calyrsoft.ucbp1.features.profile.domain.vo.Email
import com.calyrsoft.ucbp1.features.profile.domain.vo.Name
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.* // Usamos Mockito-Kotlin

// Simulación de la interfaz del repositorio
interface ProfileRepository {
    suspend fun getProfile(): ProfileModel
}

// Caso de Uso a probar
class GetProfileUseCase(private val repository: ProfileRepository) {
    suspend operator fun invoke(): ProfileModel {
        return repository.getProfile()
    }
}

class GetProfileUseCaseTest {

    // Mock del repositorio para aislar la lógica del Use Case
    private lateinit var mockRepository: ProfileRepository
    private lateinit var getProfileUseCase: GetProfileUseCase

    // Datos válidos que el repositorio simulará devolver
    private val validProfileModel = ProfileModel(
        name = Name("Carlos Mendoza"),
        email = Email("carlos.m@test.com"),
        age = Age(30),
        isVerified = true
    )

    @Before
    fun setup() {
        // Inicializar el mock y el caso de uso antes de cada prueba
        mockRepository = mock()
        getProfileUseCase = GetProfileUseCase(mockRepository)
    }

    @Test
    fun `invoke calls repository and returns ProfileModel successfully`() = runBlocking {
        // Configuración del mock: cuando se llama a getProfile, devuelve el modelo válido
        whenever(mockRepository.getProfile()).thenReturn(validProfileModel)

        // Ejecución del caso de uso
        val result = getProfileUseCase()

        // Verificación: Se llamó al método del repositorio
        verify(mockRepository).getProfile()

        // Afirmaciones: El resultado es el esperado y los datos son correctos
        assertEquals("Carlos Mendoza", result.name.value)
        assertEquals(30, result.age.value)
        assertTrue(result.isVerified)
    }

    @Test(expected = Exception::class)
    fun `invoke propagates exceptions from the repository`() = runBlocking {
        // Configuración del mock: simular un error en la capa de datos
        whenever(mockRepository.getProfile()).thenAnswer { throw Exception("Error de red simulado") }

        // Ejecución (debería lanzar la excepción)
        getProfileUseCase()

        // Verificación (asegurar que la llamada ocurrió)
        verify(mockRepository).getProfile()
    }
}