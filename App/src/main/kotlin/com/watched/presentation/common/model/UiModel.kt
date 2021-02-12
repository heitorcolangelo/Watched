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

    companion object {
        const val NO_ID = ""
    }
}
