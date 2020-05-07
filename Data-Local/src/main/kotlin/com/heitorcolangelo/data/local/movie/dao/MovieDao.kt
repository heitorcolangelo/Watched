package com.heitorcolangelo.data.local.movie.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heitorcolangelo.data.local.movie.entity.MovieEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface MovieDao {

    @Query("SELECT * FROM ${MovieEntity.TABLE_NAME}")
    fun getMovies(): Flowable<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovies(movieList: List<MovieEntity>): Completable

    @Query("DELETE FROM ${MovieEntity.TABLE_NAME}")
    fun clearMovies(): Completable
}
