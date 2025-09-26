package com.calyrsoft.ucbp1.features.movie.data.mapper

import com.calyrsoft.ucbp1.features.movie.data.local.entity.MovieEntity
import com.calyrsoft.ucbp1.features.movie.data.remote.dto.MovieDto
import com.calyrsoft.ucbp1.features.movie.domain.model.MovieModel

// Supuesto: Definición del DTO para el mapeo
data class MovieDto(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val release_date: String?,
    val vote_average: Double
)

// Supuesto: Mapeo de DTO (API) a Entity (Room)
fun MovieDto.toEntity(): MovieEntity {
    return MovieEntity(
        id = this.id,
        title = this.title,
        overview = this.overview,
        posterPath = this.poster_path,
        releaseDate = this.release_date,
        voteAverage = this.vote_average
    )
}

// Supuesto: Mapeo de Entity (Room) a Model (Dominio)
fun MovieEntity.toModel(): com.calyrsoft.ucbp1.features.movie.data.mapper.MovieModel {
    return MovieModel(
        id = this.id,
        title = this.title,
        overview = this.overview,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        voteAverage = this.voteAverage
    )
}

// Supuesto: Definición del Modelo de Dominio (para uso en ViewModel/UseCase)
data class MovieModel(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String?,
    val voteAverage: Double
)