package com.watched.domain.movie.model

import com.watched.domain.common.model.DomainModel

class TopXMovieDomainModel(
    val moviePosition: Int,
    val movie: MovieDomainModel
) : DomainModel()