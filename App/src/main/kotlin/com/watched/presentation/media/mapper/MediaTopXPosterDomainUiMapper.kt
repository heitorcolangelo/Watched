package com.watched.presentation.media.mapper

import com.watched.domain.media.MediaImageDomainModel
import com.watched.presentation.common.mapper.DomainUiMapper
import com.watched.presentation.media.ui.model.MediaTopXPosterUiModel
import javax.inject.Inject

class MediaTopXPosterDomainUiMapper @Inject constructor(
    private val movieImageDomainUiMapper: MediaImageDomainUiMapper
) : DomainUiMapper<MediaImageDomainModel, MediaTopXPosterUiModel> {

    override fun mapToUiModel(domainModel: MediaImageDomainModel): MediaTopXPosterUiModel {
        return MediaTopXPosterUiModel(
            id = domainModel.path,
            imageUiModel = movieImageDomainUiMapper.mapToUiModel(domainModel)
        )
    }
}
