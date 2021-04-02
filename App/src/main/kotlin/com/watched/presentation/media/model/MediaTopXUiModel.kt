package com.watched.presentation.media.model

import com.watched.presentation.common.model.ItemUiModel
import com.watched.presentation.common.model.UiModel

data class MediaTopXUiModel(
    override val id: String,
    val poster: MediaTopXPosterUiModel,
    val position: Int
) : ItemUiModel

data class MediaTopXPosterUiModel(
    override val id: String,
    private val imageUiModel: MediaImageUiModel
) : UiModel {
    val path: String get() = imageUiModel.path(MediaImageUiModel.Size.MEDIUM)
}