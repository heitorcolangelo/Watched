package com.watched.presentation.media.mapper

import com.watched.presentation.common.mapper.DomainUiMapper
import com.watched.presentation.media.domain.MediaTopXDomainModel
import com.watched.presentation.media.ui.model.MediaTopXUiModel
import javax.inject.Inject

class MediaTopXDomainUiMapper @Inject constructor(
    private val posterMapper: MediaTopXPosterDomainUiMapper
) : DomainUiMapper<MediaTopXDomainModel, MediaTopXUiModel> {

    override fun mapToUiModel(domainModel: MediaTopXDomainModel): MediaTopXUiModel {
        return with(domainModel) {
            MediaTopXUiModel(
                id = media.id,
                poster = posterMapper.mapToUiModel(media.poster),
                position = position
            )
        }
    }
}
