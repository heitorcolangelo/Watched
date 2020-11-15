package com.heitorcolangelo.presentation.common.model

data class FormattedDateUiModel(
    val formattedDate: String
) : UiModel {
    override val id: String = formattedDate
}
