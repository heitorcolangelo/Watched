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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dummy_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        inject()
        viewModel

        // Attempt to invoke virtual method 'android.content.Context android.content.Context.getApplicationContext()' on a null object reference
        // Falta descobrir o motivo de não estar conseguindo achar o application mas a injeção no fragment aparentemente funciona.
        // Acho que talvez eu tenha que fazer um inject() to application component.
        // Continuar se baseando pelo app plaid.
        // Tentar remover dagger-android, acredito que não está sendo necessário
    }

}
