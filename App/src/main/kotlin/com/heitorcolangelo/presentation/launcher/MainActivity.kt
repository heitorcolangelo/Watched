package com.heitorcolangelo.presentation.launcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.heitorcolangelo.R
import com.heitorcolangelo.presentation.common.ActivityIntentProvider

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = ActivityIntentProvider.Dummy.getIntent()
        startActivity(intent)
    }
}
