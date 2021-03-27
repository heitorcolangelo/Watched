package com.watched.movie.domain.model

import com.watched.domain.common.model.RawDateDomainModel
import com.watched.domain.media.MediaDomainModel
import com.watched.domain.media.MediaImageDomainModel

data class MovieDomainModel(
    override val id: String,
    override val title: String,
    override val overview: String,
    override val backdrop: MediaImageDomainModel,
    override val poster: MediaImageDomainModel,
    override val voteAverage: Float,
    override val popularity: Float,
    val releaseDate: RawDateDomainModel
) : MediaDomainModel
