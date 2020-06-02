package com.heitorcolangelo.presentation.common.view

import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

/**
 * Set action to [Snackbar] but without referencing the view.
 * @return Current [Snackbar] instance.
 */
fun Snackbar.setAction(@StringRes textResId: Int, clickListener: () -> Unit): Snackbar {
    this.setAction(textResId) {
        clickListener.invoke()
    }
    return this
}
