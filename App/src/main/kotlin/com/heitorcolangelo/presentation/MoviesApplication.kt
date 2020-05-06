package com.heitorcolangelo.presentation

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.heitorcolangelo.presentation.common.activity.BaseActivity
import com.heitorcolangelo.presentation.di.ApplicationComponent
import com.heitorcolangelo.presentation.di.DaggerApplicationComponent

class MoviesApplication : Application() {
    companion object {
        fun applicationComponent(context: Context): ApplicationComponent {
            return (context.applicationContext as MoviesApplication).applicationComponent
        }
    }

    private val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.create()
    }
}

fun Fragment.applicationComponent() = MoviesApplication.applicationComponent(requireContext())
fun BaseActivity.applicationComponent() = MoviesApplication.applicationComponent(this)
