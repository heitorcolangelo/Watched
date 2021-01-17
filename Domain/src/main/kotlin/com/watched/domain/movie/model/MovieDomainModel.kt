package com.watched.domain.movie.model

import com.watched.domain.common.model.DomainModel
import com.watched.domain.common.model.RawDateDomainModel

data class MovieDomainModel(
    private val movieId: String,
    val title: String,
    val overview: String,
    val backdrop: MovieImageDomainModel,
    val poster: MovieImageDomainModel,
    val voteAverage: Float,
    val popularity: Float,
    val releaseDate: RawDateDomainModel
) : DomainModel(movieId)
