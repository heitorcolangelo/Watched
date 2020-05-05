package com.heitorcolangelo.dummy.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.heitorcolangelo.dummy.R
import com.heitorcolangelo.dummy.databinding.FragmentDummyBinding
import com.heitorcolangelo.dummy.di.inject
import com.heitorcolangelo.presentation.common.view.binding.viewBinding
import javax.inject.Inject

class DummyFragment : Fragment(R.layout.fragment_dummy) {

    companion object {
        fun newInstance() = DummyFragment()
    }

    @Inject
    lateinit var viewModel: DummyViewModel
    private val binding: FragmentDummyBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        inject()
        viewModel
    }
}
