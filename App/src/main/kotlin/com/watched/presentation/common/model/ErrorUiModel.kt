package com.watched.presentation.common.model

data class ErrorUiModel(
    val message: String,
    val code: String
) : UiModel {
    override val id: String = code
}
