package com.watched.presentation.launcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.watched.R
import com.watched.presentation.common.activity.BaseActivity

class LauncherActivity : BaseActivity() {

    override val layoutToInflate: Int
        get() = R.layout.activity_launcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
    }
}
