package com.heitorcolangelo.presentation.common.list

import androidx.recyclerview.widget.ListAdapter
import com.heitorcolangelo.presentation.common.model.ItemUiModel

abstract class BaseAdapter<Model : ItemUiModel, ViewHolder : BaseViewHolder<Model>>
    : ListAdapter<Model, ViewHolder>(DiffItemCallback<Model>()) {

    protected var itemClickListener: ((model: Model) -> Unit)? = null

    fun onItemClicked(listener: (model: Model) -> Unit) {
        itemClickListener = listener
    }
}
