package com.heitorcolangelo.presentation.common.di

import androidx.fragment.app.Fragment

interface FragmentComponent<F : Fragment>  {
    fun inject(fragment: F)
}