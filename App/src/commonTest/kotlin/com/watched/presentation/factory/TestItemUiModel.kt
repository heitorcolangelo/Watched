package com.watched.presentation.factory

import androidx.annotation.RestrictTo
import com.watched.presentation.common.model.ItemUiModel

@RestrictTo(RestrictTo.Scope.TESTS)
data class TestItemUiModel(
    override val id: String
) : ItemUiModel {
    override fun areContentsTheSame(other: ItemUiModel): Boolean {
        return this == other
    }
}
