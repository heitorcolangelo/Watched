package com.heitorcolangelo.presentation.common.view

import androidx.recyclerview.widget.RecyclerView
import com.heitorcolangelo.presentation.common.helper.observer
import com.heitorcolangelo.presentation.common.model.UiModel

abstract class BaseAdapter<Model : UiModel, ViewHolder : BaseViewHolder<Model>> :
    RecyclerView.Adapter<ViewHolder>() {

    var items: List<Model> by observer(listOf()) {
        notifyDataSetChanged()
    }

    protected var itemClickListener: ((model: Model) -> Unit)? = null

    abstract fun onItemClicked(listener: (model: Model) -> Unit)
}
