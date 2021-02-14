package com.watched.presentation.common.model

interface UiModel {
    val id: String
}

interface ItemUiModel : UiModel {

    /**
     * Compare contents between two UiModel classes.
     *
     * @return True if contents are the same, false otherwise.
     */
    fun areContentsTheSame(other: ItemUiModel): Boolean {
        return this == other
    }
}

interface ListUiModel<Model : UiModel> : UiModel {
    val items: List<Model>
}