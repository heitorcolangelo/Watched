package com.watched.data.local.movie.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.watched.data.local.movie.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM ${MovieEntity.TABLE_NAME}")
    suspend fun getMovies(): List<MovieEntity>

    @Query("SELECT * FROM ${MovieEntity.TABLE_NAME} LIMIT :pageSize OFFSET :offset")
    suspend fun getPagedMovies(pageSize: Int, offset: Int): List<MovieEntity>

    @Query("SELECT * FROM ${MovieEntity.TABLE_NAME} WHERE id = :movieId")
    suspend fun getMovie(movieId: String): MovieEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movieList: List<MovieEntity>)

    @Query("DELETE FROM ${MovieEntity.TABLE_NAME}")
    suspend fun clearMovies()
}
