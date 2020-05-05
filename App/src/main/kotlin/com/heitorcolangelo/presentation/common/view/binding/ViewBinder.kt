package com.heitorcolangelo.presentation.common.view.binding

import android.view.View
import androidx.annotation.RestrictTo
import androidx.viewbinding.ViewBinding

interface ViewBinder<VB : ViewBinding> {
    fun bind(view: View): VB
}

@PublishedApi
@RestrictTo(RestrictTo.Scope.LIBRARY)
internal inline fun <VB : ViewBinding> viewBinder(
    crossinline bindView: (View) -> VB
): ViewBinder<VB> {
    return object : ViewBinder<VB> {
        override fun bind(view: View) = bindView(view)
    }
}
