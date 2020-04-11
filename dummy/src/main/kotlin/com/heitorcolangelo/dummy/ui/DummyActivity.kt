package com.heitorcolangelo.dummy.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.heitorcolangelo.dummy.R

class DummyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dummy)

        supportFragmentManager.beginTransaction().add(
            DummyFragment.newInstance(), ""
        ).commit()
    }
}
