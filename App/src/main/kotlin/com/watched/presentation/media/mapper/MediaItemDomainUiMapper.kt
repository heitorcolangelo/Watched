package com.watched.presentation.media.mapper

import com.watched.domain.media.MediaDomainModel
import com.watched.presentation.common.mapper.DomainUiMapper
import com.watched.presentation.media.ui.model.MediaItemUiModel
import javax.inject.Inject

class MediaItemDomainUiMapper @Inject constructor(
    private val imageMapper: MediaImageDomainUiMapper
) : DomainUiMapper<MediaDomainModel, MediaItemUiModel> {

    override fun mapToUiModel(domainModel: MediaDomainModel): MediaItemUiModel {
        return with(domainModel) {
            MediaItemUiModel(
                id = id,
                poster = imageMapper.mapToUiModel(poster)
            )
        }
    }
}
