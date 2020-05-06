package com.heitorcolangelo.presentation.launcher

import android.os.Bundle
import com.heitorcolangelo.presentation.common.activity.BaseActivity
import com.heitorcolangelo.presentation.common.provider.ActivityIntentProvider

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = ActivityIntentProvider.Movie.getIntent()
        startActivity(intent)
    }
}
