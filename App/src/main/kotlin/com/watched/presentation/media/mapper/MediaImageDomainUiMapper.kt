package com.watched.presentation.media.mapper

import com.watched.data.remote.common.BuildConfiguration
import com.watched.domain.media.MediaImageDomainModel
import com.watched.presentation.common.mapper.DomainUiMapper
import com.watched.presentation.media.model.MediaImageUiModel
import javax.inject.Inject

class MediaImageDomainUiMapper @Inject constructor(
    private val buildConfig: BuildConfiguration
) : DomainUiMapper<MediaImageDomainModel, MediaImageUiModel> {

    override fun mapToUiModel(domainModel: MediaImageDomainModel): MediaImageUiModel {
        return MediaImageUiModel(buildConfig.imageBaseUrl(), domainModel.path)
    }

}
