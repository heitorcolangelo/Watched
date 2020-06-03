package com.heitorcolangelo.presentation.common.model

data class ErrorUiModel(
    val message: String,
    val code: String
) : UiModel()
