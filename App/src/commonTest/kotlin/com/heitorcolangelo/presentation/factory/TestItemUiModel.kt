package com.heitorcolangelo.presentation.factory

import androidx.annotation.RestrictTo
import com.heitorcolangelo.presentation.common.model.ItemUiModel

@RestrictTo(RestrictTo.Scope.TESTS)
data class TestItemUiModel(
    override val id: String
) : ItemUiModel() {
    override fun areContentsTheSame(other: ItemUiModel): Boolean {
        return this == other
    }
}
