package com.watched.presentation.media.model

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

    fun getUrl(size: Size) = baseUrl + size.value + imagePath
}
