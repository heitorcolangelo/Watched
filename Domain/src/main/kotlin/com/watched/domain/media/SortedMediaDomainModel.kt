package com.watched.domain.media

import com.watched.domain.common.model.DomainModel
import com.watched.domain.common.model.SortOptionsDomainModel

data class SortedMediaDomainModel(
    val items: List<MediaDomainModel>,
    val sortOptions: SortOptionsDomainModel? = null
) : DomainModel
