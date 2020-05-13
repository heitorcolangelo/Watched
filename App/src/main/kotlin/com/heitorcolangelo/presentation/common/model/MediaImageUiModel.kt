package com.heitorcolangelo.presentation.common.model

sealed class MediaImageUiModel(
    private val baseUrl: String,
    private val imagePath: String,
    private val size: SizePath
) : UiModel() {
    enum class SizePath(val value: String) {
        SMALL("w154"),
        MEDIUM("w342"),
        LARGE("w500")
    }

    fun getFullUrl(): String {
        return baseUrl + size.value + imagePath
    }

    class Small(baseUrl: String, imagePath: String) :
        MediaImageUiModel(baseUrl, imagePath, SizePath.SMALL)

    class Medium(baseUrl: String, imagePath: String) :
        MediaImageUiModel(baseUrl, imagePath, SizePath.MEDIUM)

    class Large(baseUrl: String, imagePath: String) :
        MediaImageUiModel(baseUrl, imagePath, SizePath.LARGE)
}
