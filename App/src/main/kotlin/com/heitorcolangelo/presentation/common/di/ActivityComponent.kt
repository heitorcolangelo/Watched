package com.heitorcolangelo.presentation.common.di

import android.app.Activity

interface ActivityComponent<A : Activity> {
    fun inject(activity: A)
}