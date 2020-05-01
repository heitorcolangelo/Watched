package com.heitorcolangelo.dummy.ui

import android.os.Bundle
import com.heitorcolangelo.presentation.common.activity.BaseActivity

class DummyActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().add(
            DummyFragment.newInstance(), ""
        ).commit()
    }
}
