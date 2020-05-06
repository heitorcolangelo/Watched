package com.heitorcolangelo.movie.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.heitorcolangelo.movie.R
import com.heitorcolangelo.movie.databinding.FragmentMovieListBinding
import com.heitorcolangelo.movie.di.inject
import com.heitorcolangelo.presentation.common.view.binding.viewBinding
import javax.inject.Inject

class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    companion object {
        fun newInstance() = MovieListFragment()
    }

    @Inject
    lateinit var viewModel: MovieListViewModel
    private val binding: FragmentMovieListBinding by viewBinding()

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
