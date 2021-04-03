package com.watched.movie.data.factory

import com.watched.common.test.MockFactory
import com.watched.presentation.media.ui.model.MediaImageUiModel
import com.watched.presentation.media.ui.model.MediaItemUiModel

object MediaItemUiModelFactory : MockFactory<MediaItemUiModel> {
    override fun make(): MediaItemUiModel {
        val mediaImage = MediaImageUiModel(randomString(), randomString())
        return MediaItemUiModel(randomId(), mediaImage)
    }
}
