package com.heitorcolangelo.domain.movie.model

import com.heitorcolangelo.domain.common.model.DomainModel

data class MovieImageDomainModel(
    val path: String
) : DomainModel()
