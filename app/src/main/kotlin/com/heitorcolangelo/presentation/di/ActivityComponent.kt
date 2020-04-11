package com.heitorcolangelo.presentation.di

import android.app.Activity

interface ActivityComponent<T : Activity> {
    fun inject(activity: T)
}