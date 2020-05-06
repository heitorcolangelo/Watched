package com.heitorcolangelo.movie.ui

import android.os.Bundle
import com.heitorcolangelo.presentation.common.activity.BaseActivity

class MovieActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().add(
            MovieListFragment.newInstance(), ""
        ).commit()
    }
}
