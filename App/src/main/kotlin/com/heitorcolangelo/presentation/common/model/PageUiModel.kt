package com.heitorcolangelo.presentation.common.model

data class PageUiModel<Model : ItemUiModel>(
    val items: List<Model>,
    val error: ErrorUiModel?
) : UiModel() {
    fun containsError() = error != null
}
