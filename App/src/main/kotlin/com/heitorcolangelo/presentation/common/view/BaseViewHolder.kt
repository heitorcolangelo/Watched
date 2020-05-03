package com.heitorcolangelo.presentation.common.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.heitorcolangelo.presentation.common.model.UiModel
import kotlinx.android.extensions.LayoutContainer

abstract class BaseViewHolder<Model : UiModel>(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    abstract fun bind(model: UiModel)
}
