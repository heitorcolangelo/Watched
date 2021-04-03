package com.watched.presentation.media.model

import androidx.annotation.StringRes
import com.watched.presentation.common.model.ItemUiModel

data class MediaSectionItemUiModel(
    override val id: String,
    @StringRes val title: Int,
    val list: List<MediaItemUiModel>
) : ItemUiModel
