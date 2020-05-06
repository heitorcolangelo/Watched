package com.heitorcolangelo.presentation.common.model

const val smallPosterSize = "w154"
const val mediumPosterSize = "w342"
const val largePosterSize = "w500"

sealed class PosterUiModel(
    private val baseUrl: String = "",
    private val size: String,
    private val imagePath: String = ""
) : UiModel() {
    fun getFullUrl(): String {
        return baseUrl + size + imagePath
    }

    class SmallPosterUiModel(baseUrl: String, imagePath: String) :
        PosterUiModel(baseUrl = baseUrl, size = smallPosterSize, imagePath = imagePath)

    class MediumPosterUiModel(baseUrl: String, imagePath: String) :
        PosterUiModel(baseUrl = baseUrl, size = mediumPosterSize, imagePath = imagePath)

    class LargePosterUiModel(baseUrl: String, imagePath: String) :
        PosterUiModel(baseUrl = baseUrl, size = largePosterSize, imagePath = imagePath)
}
