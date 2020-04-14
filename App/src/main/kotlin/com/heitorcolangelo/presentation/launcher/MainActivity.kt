package com.heitorcolangelo.presentation.launcher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.heitorcolangelo.skeleton.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(Intent.ACTION_VIEW).setClassName(
            "com.heitorcolangelo.skeleton",
            "com.heitorcolangelo.dummy.ui.DummyActivity"
        )
        startActivity(intent)
    }
}

