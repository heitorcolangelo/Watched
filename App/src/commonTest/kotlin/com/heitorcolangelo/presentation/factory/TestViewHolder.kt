package com.heitorcolangelo.presentation.factory

import android.view.View
import androidx.annotation.RestrictTo
import com.heitorcolangelo.presentation.common.list.BaseViewHolder

@RestrictTo(RestrictTo.Scope.TESTS)
class TestViewHolder(
    override val containerView: View
) : BaseViewHolder<TestItemUiModel>(containerView) {
    override fun bind(model: TestItemUiModel) {
    }
}
