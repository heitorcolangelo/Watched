package com.heitorcolangelo.presentation.common.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object ViewModelFactory {
    inline fun <reified T : ViewModel> make(
        fragment: Fragment,
        crossinline factory: () -> T
    ): T {
        @Suppress("UNCHECKED_CAST")
        val vmFactory = object : ViewModelProvider.Factory {
            override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
        }

        return ViewModelProvider(fragment, vmFactory).get(T::class.java)
    }
}