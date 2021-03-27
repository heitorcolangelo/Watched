package com.watched.movie.data.factory

import com.watched.common.test.MockFactory
import com.watched.presentation.media.model.MediaImageUiModel
import com.watched.presentation.media.model.MediaItemUiModel

object MediaItemUiModelFactory : MockFactory<MediaItemUiModel> {
    override fun make(): MediaItemUiModel {
        val mediaImage = MediaImageUiModel(randomString(), randomString())
        return MediaItemUiModel(randomId(), mediaImage)
    }
}
