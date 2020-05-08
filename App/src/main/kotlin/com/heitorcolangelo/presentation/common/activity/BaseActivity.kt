package com.heitorcolangelo.presentation.common.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentContainerView
import com.heitorcolangelo.data.local.di.LocalDataModule
import com.heitorcolangelo.data.remote.di.RemoteDataModule
import com.heitorcolangelo.databinding.ActivityBaseBinding
import com.heitorcolangelo.presentation.applicationComponent
import com.heitorcolangelo.presentation.common.provider.BuildConfigurationProvider

abstract class BaseActivity : AppCompatActivity() {
    val toolbar: Toolbar get() = binding.toolbar
    val fragmentContainer: FragmentContainerView get() = binding.fragmentContainer

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        DaggerBaseActivityComponent.builder()
            .applicationComponent(this.applicationComponent())
            .localDataModule(LocalDataModule(application))
            .remoteDataModule(RemoteDataModule(BuildConfigurationProvider()))
            .activity(this)
            .build()
            .inject(this)
    }
}
