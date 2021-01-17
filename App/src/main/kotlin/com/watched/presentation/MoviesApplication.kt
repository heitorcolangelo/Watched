package com.watched.presentation

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.facebook.drawee.backends.pipeline.Fresco
import com.jakewharton.threetenabp.AndroidThreeTen
import com.watched.BuildConfig
import com.watched.presentation.common.activity.BaseActivity
import com.watched.presentation.di.ApplicationComponent
import com.watched.presentation.di.DaggerApplicationComponent
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
        Fresco.initialize(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}

fun Fragment.applicationComponent() = MoviesApplication.applicationComponent(requireContext())
fun BaseActivity.applicationComponent() = MoviesApplication.applicationComponent(this)
