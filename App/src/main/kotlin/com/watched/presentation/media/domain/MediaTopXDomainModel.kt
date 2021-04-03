package com.watched.presentation.media.domain

import com.watched.domain.common.model.DomainModel

class MovieTopXDomainModel(
    private val moviePosition: Int,
    val movie: MovieDomainModel
) : DomainModel {
    val position: Int
        get() = moviePosition + MOVIE_POSITION_START

    companion object {
        const val MOVIE_POSITION_START = 1
    }
}
