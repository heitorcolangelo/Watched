package com.heitorcolangelo.presentation.common.model

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
