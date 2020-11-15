package com.heitorcolangelo.presentation.common.view

import android.view.View

sealed class Visibility(val value: Int) {
    object Invisible : Visibility(View.INVISIBLE)
    object Visible : Visibility(View.VISIBLE)
    object Gone : Visibility(View.GONE)
}

fun View.visibility(visibility: Visibility) {
    this.visibility = visibility.value
}