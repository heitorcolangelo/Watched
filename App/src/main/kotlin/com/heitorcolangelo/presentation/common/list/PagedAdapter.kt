package com.heitorcolangelo.presentation.common.list

import androidx.annotation.RestrictTo
import com.heitorcolangelo.presentation.common.model.ItemUiModel

abstract class PagedAdapter<Model : ItemUiModel, ViewHolder : BaseViewHolder<Model>>(
    private val paginationListener: PaginationListener
) : BaseAdapter<Model, ViewHolder>() {
    interface PaginationListener {
        fun requestPage()
    }

    private var isLoadingPage = false

    override fun getItemViewType(position: Int): Int {
        if (position >= itemCount - 1 && !isLoadingPage) {
            isLoadingPage = true
            paginationListener.requestPage()
        }
        return super.getItemViewType(position)
    }

    override fun submitList(list: MutableList<Model>?) {
        isLoadingPage = false
        super.submitList(list)
    }

    @RestrictTo(RestrictTo.Scope.TESTS)
    fun isLoadingPage() = isLoadingPage
}