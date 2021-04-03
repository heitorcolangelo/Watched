package com.watched.presentation.media.ui.model

import com.watched.presentation.common.model.UiModel

class MediaImageUiModel(
    private val baseUrl: String,
    private val imagePath: String
) : UiModel {
    override val id: String = imagePath

    enum class Size(val value: String) {
        SMALL("w154"),
        MEDIUM("w342"),
        LARGE("w500")
    }

    fun path(size: Size = Size.SMALL) = baseUrl + size.value + imagePath
}
