package com.watched.presentation.media.ui.model

import com.watched.presentation.common.model.ItemUiModel

data class MediaItemUiModel(
    override val id: String,
    val poster: MediaImageUiModel,
) : ItemUiModel
