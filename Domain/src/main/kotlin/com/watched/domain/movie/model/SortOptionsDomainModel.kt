package com.watched.domain.movie.model

import com.watched.domain.common.model.DomainModel

sealed class SortOptionsDomainModel : DomainModel() {
    object Popularity : SortOptionsDomainModel()
    object TopRated : SortOptionsDomainModel()
}
