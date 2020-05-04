package com.heitorcolangelo.domain.movie.model

import com.heitorcolangelo.domain.common.model.DomainModel

data class MovieDomainModel(
    private val movieId: String,
    val title: String,
    val overview: String,
    val backdropPath: String,
    val posterPath: String,
    val voteAverage: String,
    val popularity: String,
    val releaseDate: String
) : DomainModel(movieId)