package com.heitorcolangelo.presentation

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.heitorcolangelo.BuildConfig
import com.heitorcolangelo.presentation.common.activity.BaseActivity
import com.heitorcolangelo.presentation.di.ApplicationComponent
import com.heitorcolangelo.presentation.di.DaggerApplicationComponent
import com.jakewharton.threetenabp.AndroidThreeTen
import timber.log.Timber
import timber.log.Timber.DebugTree

class MoviesApplication : Application() {
    companion object {
        fun applicationComponent(context: Context): ApplicationComponent {
            return (context.applicationContext as MoviesApplication).applicationComponent
        }
    }

    private val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.create()
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}

fun Fragment.applicationComponent() = MoviesApplication.applicationComponent(requireContext())
fun BaseActivity.applicationComponent() = MoviesApplication.applicationComponent(this)
