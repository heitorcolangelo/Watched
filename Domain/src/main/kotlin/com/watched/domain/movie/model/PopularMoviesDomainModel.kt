package com.watched.domain.movie.model

import com.watched.domain.common.model.DomainModel

data class PopularMoviesDomainModel(
    val movies: List<MovieDomainModel>
) : DomainModel