package com.heitorcolangelo.presentation.common.list

import androidx.annotation.RestrictTo
import com.heitorcolangelo.presentation.common.model.ItemUiModel
import com.heitorcolangelo.presentation.common.model.PageUiModel

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

    fun submitPage(page: PageUiModel<Model>) {
        isLoadingPage = false
        when {
            page.containsError() -> {
                // Here you can handle error inside the adapter if necessary.
            }
            page.items.isEmpty() -> {
                super.submitList(null)
            }
            else -> {
                val newList = currentList.toMutableList()
                newList.addAll(page.items)
                super.submitList(newList)
            }
        }
    }

    @RestrictTo(RestrictTo.Scope.TESTS)
    fun isLoadingPage() = isLoadingPage
}
