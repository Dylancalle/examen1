package com.calyrsoft.ucbp1.features.movie.data.repository

import com.calyrsoft.ucbp1.features.movie.data.local.dao.MovieDao
import com.calyrsoft.ucbp1.features.movie.data.mapper.toEntity
import com.calyrsoft.ucbp1.features.movie.data.remote.MovieApiSource
import com.calyrsoft.ucbp1.features.movie.domain.model.MovieModel
import com.calyrsoft.ucbp1.features.movie.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val apiSource: MovieApiSource, // Asumimos la fuente de la API
    private val movieDao: MovieDao
) : MovieRepository {

    // ... otras funciones del repositorio

    override suspend fun getPopularMovies(): List<MovieModel> {
        return try {
            // 1. Llamar a la API REST para obtener las películas
            val movieDtos = apiSource.getPopularMovies()

            // 2. Si la consulta es exitosa, mapear a entidades de Room
            val movieEntities = movieDtos.map { it.toEntity() }

            // 3. Guardar/Actualizar la lista en la base de datos local (upsert)
            movieDao.insertMovies(movieEntities)

            // 4. Mapear y devolver el resultado (opcional, podrías devolver el resultado de Room si usas Flow)
            // Aquí devolvemos los modelos directamente de la API para mantener el comportamiento actual del UseCase/ViewModel.
            movieDtos.map { /* Mapeo de DTO a Model */ } // Asumiendo que hay un mapeo DTO -> Model
        } catch (e: Exception) {
            // Manejar errores de red/API. En caso de error, podríamos intentar cargar de Room.
            // Para esta implementación, solo devolvemos una lista vacía o lanzamos el error.
            throw e
        }
    }
}