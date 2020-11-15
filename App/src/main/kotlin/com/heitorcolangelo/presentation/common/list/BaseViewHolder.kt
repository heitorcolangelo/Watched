package com.heitorcolangelo.presentation.common.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class BaseViewHolder<Binding : ViewBinding> private constructor(
    val binding: Binding
) : RecyclerView.ViewHolder(binding.root) {
    constructor(
        parent: ViewGroup,
        creator: (inflater: LayoutInflater, root: ViewGroup, attachToRoot: Boolean) -> Binding
    ) : this(
        creator(LayoutInflater.from(parent.context), parent, false)
    )

    companion object {
        fun <Binding : ViewBinding> ViewGroup.viewHolderFrom(
            creator: (inflater: LayoutInflater, root: ViewGroup, attachToRoot: Boolean) -> Binding
        ): BaseViewHolder<Binding> = BaseViewHolder(this, creator)
    }
}
