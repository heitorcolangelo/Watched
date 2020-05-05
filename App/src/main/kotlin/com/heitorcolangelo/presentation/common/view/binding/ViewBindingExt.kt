package com.heitorcolangelo.presentation.common.view.binding

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty

@Suppress("unused")
inline fun <VB : ViewBinding> Fragment.viewBinding(
    crossinline bindView: (View) -> VB
): ReadOnlyProperty<Fragment, VB> {
    return FragmentViewBindingProperty(viewBinder(bindView))
}

@Suppress("unused")
inline fun <reified VB : ViewBinding> Fragment.viewBinding(): ReadOnlyProperty<Fragment, VB> {
    return FragmentViewBindingProperty(DefaultViewBinder(VB::class.java))
}