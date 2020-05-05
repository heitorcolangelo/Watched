package com.heitorcolangelo.presentation.common.view.binding

import android.view.View
import androidx.annotation.RestrictTo
import androidx.viewbinding.ViewBinding

@RestrictTo(RestrictTo.Scope.LIBRARY)
@PublishedApi
internal class DefaultViewBinder<VB : ViewBinding>(
    private val viewBindingClass: Class<VB>
) : ViewBinder<VB> {

    private val bindViewMethod by lazy(LazyThreadSafetyMode.NONE) {
        viewBindingClass.getMethod("bind", View::class.java)
    }

    @Suppress("UNCHECKED_CAST")
    override fun bind(view: View): VB {
        return bindViewMethod(null, view) as VB
    }
}