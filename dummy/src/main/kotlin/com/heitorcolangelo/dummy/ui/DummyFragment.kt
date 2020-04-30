package com.heitorcolangelo.dummy.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.heitorcolangelo.dummy.R
import com.heitorcolangelo.dummy.di.inject
import javax.inject.Inject

class DummyFragment : Fragment() {

    companion object {
        fun newInstance() = DummyFragment()
    }

    @Inject
    lateinit var viewModel: DummyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dummy_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        inject()
        viewModel
    }
}
