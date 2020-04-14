package com.heitorcolangelo.dummy.di

import com.heitorcolangelo.dummy.ui.DummyFragment

internal fun DummyFragment.inject() {
    DaggerDummyFeatureComponent.builder()
        .fragment(this)
        .build()
        .inject(this)
}