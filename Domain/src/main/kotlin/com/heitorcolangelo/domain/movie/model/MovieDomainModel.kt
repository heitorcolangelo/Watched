package com.heitorcolangelo.domain.movie.model

import com.heitorcolangelo.domain.common.model.DomainModel
import java.util.Date

data class MovieDomainModel(
    private val movieId: String,
    val title: String,
    val overview: String,
    val backdropPath: String,
    val posterPath: String,
    val voteAverage: Float,
    val popularity: Float,
    val releaseDate: Date
) : DomainModel(movieId)
