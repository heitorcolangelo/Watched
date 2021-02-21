package com.watched.domain.movie.model

import com.watched.domain.common.model.ListDomainModel

data class MovieListDomainModel(
    override val id: String,
    override val items: List<MovieDomainModel>,
    val sortOptions: SortOptionsDomainModel? = null
) : ListDomainModel<MovieDomainModel>
