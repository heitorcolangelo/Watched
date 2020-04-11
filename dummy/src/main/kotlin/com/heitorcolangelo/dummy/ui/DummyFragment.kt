package com.heitorcolangelo.dummy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.heitorcolangelo.dummy.R
import javax.inject.Inject

class DummyFragment : Fragment() {

    companion object {
        fun newInstance() = DummyFragment()
    }

    @Inject
    lateinit var viewModelFactory: DummyViewModel.Factory
    private lateinit var viewModel: DummyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dummy_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = viewModelFactory.create(DummyViewModel::class.java)
    }

}
