package com.heitorcolangelo.movie.ui.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.heitorcolangelo.movie.R
import com.heitorcolangelo.movie.databinding.FragmentMovieListBinding
import com.heitorcolangelo.movie.di.inject
import com.heitorcolangelo.movie.model.MovieItemUiModel
import com.heitorcolangelo.movie.ui.MovieActivity
import com.heitorcolangelo.movie.ui.detail.MovieDetailsFragment
import com.heitorcolangelo.presentation.common.view.binding.viewBinding
import javax.inject.Inject

class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    companion object {
        fun newInstance() = MovieListFragment()
    }

    @Inject
    lateinit var viewModel: MovieListViewModel
    private val binding: FragmentMovieListBinding by viewBinding()
    private val layoutManager: FlexboxLayoutManager by lazy { setupLayoutManager() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = layoutManager
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        inject()
        viewModel.movies.observe(this, Observer { onMovies(it) })
        viewModel.navigation.observe(this, Observer { onNavigation(it) })
    }

    private fun onNavigation(navigation: MovieListViewModel.Navigation) {
        when (navigation) {
            is MovieListViewModel.Navigation.MovieDetails -> {
                val detailsFragment = MovieDetailsFragment.newInstance(navigation.movieId)
                with(requireActivity() as MovieActivity) {
                    supportFragmentManager.beginTransaction()
                        .replace(fragmentContainer.id, detailsFragment)
                        .commit()
                }
            }
        }
    }

    private fun onMovies(movies: List<MovieItemUiModel>) {
        binding.recyclerView.adapter = MovieListAdapter(movies).also {
            it.onItemClicked(viewModel::onItemClicked)
        }
    }

    private fun setupLayoutManager(): FlexboxLayoutManager {
        return FlexboxLayoutManager(requireContext()).apply {
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.SPACE_EVENLY
        }
    }
}
