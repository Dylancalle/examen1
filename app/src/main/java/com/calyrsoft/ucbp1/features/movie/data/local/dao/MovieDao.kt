package com.calyrsoft.ucbp1.features.movie.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.calyrsoft.ucbp1.features.movie.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    /**
     * Inserta o actualiza una lista de películas en la base de datos local.
     * OnConflictStrategy.REPLACE realiza la operación de "upsert".
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    /**
     * Recupera todas las películas populares de la base de datos.
     * Se usa Flow para observar cambios.
     */
    @Query("SELECT * FROM movies ORDER BY vote_average DESC")
    fun getPopularMovies(): Flow<List<MovieEntity>>

    /**
     * Opcional: Para limpiar la caché si es necesario.
     */
    @Query("DELETE FROM movies")
    suspend fun clearMovies()
}