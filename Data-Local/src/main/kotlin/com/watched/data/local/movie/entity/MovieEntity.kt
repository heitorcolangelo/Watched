package com.watched.data.local.movie.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.watched.data.local.common.entity.BaseEntity

@Entity(tableName = MovieEntity.TABLE_NAME)
data class MovieEntity(
    @PrimaryKey val id: String,
    val title: String,
    val overview: String,
    val backdropPath: String,
    val posterPath: String,
    val voteAverage: Float,
    val popularity: Float,
    val releaseDate: String
) : BaseEntity {
    companion object {
        const val TABLE_NAME = "movie"
    }
}
