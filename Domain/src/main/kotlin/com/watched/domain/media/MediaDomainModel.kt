package com.watched.domain.media

import com.watched.domain.common.model.DomainModel

interface MediaDomainModel : DomainModel {
    val id: String
    val title: String
    val overview: String
    val backdrop: MediaImageDomainModel
    val poster: MediaImageDomainModel
    val voteAverage: Float
    val popularity: Float
}
