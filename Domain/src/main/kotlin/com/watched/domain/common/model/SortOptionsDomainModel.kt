package com.watched.domain.common.model

sealed class SortOptionsDomainModel : DomainModel {
    object Popularity : SortOptionsDomainModel()
    object TopRated : SortOptionsDomainModel()
}
