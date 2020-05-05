package com.heitorcolangelo.data.movie.model

import com.heitorcolangelo.data.common.model.DataModel
import java.util.Date

data class MovieDataModel(
    private val movieId: String,
    val title: String,
    val overview: String,
    val backdropPath: String,
    val posterPath: String,
    val voteAverage: Float,
    val popularity: Float,
    val releaseDate: Date
) : DataModel(movieId)
