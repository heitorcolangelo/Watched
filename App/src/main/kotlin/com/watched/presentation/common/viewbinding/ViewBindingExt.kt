package com.watched.presentation.common.viewbinding

import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
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

fun ViewBinding.makeSnackBar(
    @StringRes messageResId: Int,
    @BaseTransientBottomBar.Duration duration: Int
): Snackbar {
    return Snackbar.make(this.root, messageResId, duration)
}
