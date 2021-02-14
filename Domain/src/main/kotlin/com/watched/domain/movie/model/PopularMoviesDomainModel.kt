package com.watched.domain.movie.model

import com.watched.domain.common.model.ListDomainModel

data class PopularMoviesDomainModel(
    override val id: String,
    override val items: List<MovieDomainModel>
) : ListDomainModel<MovieDomainModel>
