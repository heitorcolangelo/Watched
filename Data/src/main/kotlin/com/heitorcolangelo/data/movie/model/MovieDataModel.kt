package com.heitorcolangelo.data.movie.model

import com.heitorcolangelo.data.common.model.DataModel

data class MovieDataModel(
    private val movieId: String,
    val title: String,
    val overview: String,
    val backdropPath: String,
    val posterPath: String,
    val voteAverage: String,
    val popularity: String,
    val releaseDate: String
): DataModel(movieId)