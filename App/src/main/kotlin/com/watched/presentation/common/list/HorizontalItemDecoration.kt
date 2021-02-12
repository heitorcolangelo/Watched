package com.watched.presentation.common.list

import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.watched.R
import timber.log.Timber

class HorizontalItemDecoration(
    context: Context
) : DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL) {

    private var divider: Drawable? = null
    private var edgeDivider: Drawable? = context.getDrawableSafely(R.drawable.item_decoration_edge)

    init {
        context.getDrawableSafely(R.drawable.item_decoration)?.let {
            setDrawable(it)
            divider = it
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        parent.adapter?.let { adapter ->
            val position = parent.getChildAdapterPosition(view)
            if (position == RecyclerView.NO_POSITION) {
                return
            }

            when (position) {
                0 -> { // First
                    val edgeWidth = edgeDivider?.intrinsicWidth ?: 0
                    val width = divider?.intrinsicWidth ?: 0
                    outRect.set(edgeWidth, 0, width, 0)
                }
                adapter.itemCount - 1 -> { // Last
                    val edgeWidth = edgeDivider?.intrinsicWidth ?: 0
                    val width = divider?.intrinsicWidth ?: 0
                    outRect.set(width, 0, edgeWidth, 0)
                }
                else -> {
                    val width = divider?.intrinsicWidth ?: 0
                    outRect.set(width, 0, width, 0)
                }
            }
        }
    }

    private fun Context.getDrawableSafely(
        @DrawableRes drawableId: Int
    ): Drawable? {
        return try {
            ResourcesCompat.getDrawable(resources, drawableId, theme)
        } catch (exception: Resources.NotFoundException) {
            Timber.e(exception)
            return null
        }
    }
}