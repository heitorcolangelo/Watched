package com.watched.presentation.common.list

import androidx.recyclerview.widget.DiffUtil
import com.watched.presentation.common.model.ItemUiModel

class DiffItemCallback<Model : ItemUiModel> : DiffUtil.ItemCallback<Model>() {
    override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean {
        return oldItem.areContentsTheSame(newItem)
    }
}
