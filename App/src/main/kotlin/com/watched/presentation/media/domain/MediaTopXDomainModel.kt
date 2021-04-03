package com.watched.presentation.media.domain

import com.watched.domain.common.model.DomainModel
import com.watched.domain.media.MediaDomainModel

class MediaTopXDomainModel(
    private val mediaPosition: Int,
    val media: MediaDomainModel
) : DomainModel {
    val position: Int
        get() = mediaPosition + MEDIA_POSITION_START

    companion object {
        const val MEDIA_POSITION_START = 1
    }
}
