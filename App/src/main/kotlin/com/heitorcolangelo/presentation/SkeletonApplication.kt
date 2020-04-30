package com.heitorcolangelo.presentation

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.heitorcolangelo.presentation.di.ApplicationComponent
import com.heitorcolangelo.presentation.di.DaggerApplicationComponent

class SkeletonApplication : Application() {
    companion object {
        fun applicationComponent(context: Context): ApplicationComponent {
            return (context.applicationContext as SkeletonApplication).applicationComponent
        }
    }

    private val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.create()
    }
}

fun Fragment.applicationComponent() = SkeletonApplication.applicationComponent(requireContext())