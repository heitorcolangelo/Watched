package com.watched.presentation.media.model

import com.watched.presentation.common.model.ItemUiModel

data class MediaItemUiModel(
    override val id: String,
    val poster: MediaImageUiModel,
) : ItemUiModel
