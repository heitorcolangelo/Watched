package com.watched.presentation.common.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.watched.presentation.common.list.BaseViewHolder.Companion.viewHolderFrom
import com.watched.presentation.common.model.ItemUiModel

class BaseAdapter<Binding : ViewBinding, Model : ItemUiModel>(
    private val inflater: (inflater: LayoutInflater, container: ViewGroup, attach: Boolean) -> Binding,
    private val binder: Binder<Binding, Model>,
) : ListAdapter<Model, BaseViewHolder<Binding>>(DiffItemCallback()) {

    interface Binder<Binding : ViewBinding, Model : ItemUiModel> {
        fun bindListItem(binding: Binding, model: Model)
    }

    private var itemClickListener: ((model: Model) -> Unit)? = null

    fun onItemClicked(listener: (model: Model) -> Unit) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Binding> {
        return parent.viewHolderFrom(inflater)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Binding>, position: Int) {
        val item = getItem(position)
        binder.bindListItem(holder.binding, item)
        holder.binding.root.setOnClickListener {
            itemClickListener?.invoke(item)
        }
    }
}
