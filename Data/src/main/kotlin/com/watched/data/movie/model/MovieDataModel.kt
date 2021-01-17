package com.watched.data.movie.model

import com.watched.data.common.model.DataModel

data class MovieDataModel(
    private val movieId: String,
    val title: String,
    val overview: String,
    val backdropPath: String,
    val posterPath: String,
    val voteAverage: Float,
    val popularity: Float,
    val releaseDate: String
) : DataModel(movieId)
