package com.heitorcolangelo.presentation.common.model

internal const val NO_ID = "NO_ID"

abstract class UiModel(open val id: String = NO_ID)

abstract class ItemUiModel(override val id: String = NO_ID) : UiModel(id) {
    /**
     * Compare contents between two UiModel classes.
     *
     * @return True if contents are the same, false otherwise.
     */
    abstract fun areContentsTheSame(other: ItemUiModel): Boolean
}
