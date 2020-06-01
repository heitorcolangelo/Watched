package com.heitorcolangelo.movie.ui.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.heitorcolangelo.movie.R
import com.heitorcolangelo.movie.databinding.FragmentMovieListBinding
import com.heitorcolangelo.movie.di.inject
import com.heitorcolangelo.movie.model.MovieItemUiModel
import com.heitorcolangelo.presentation.common.list.PagedAdapter
import com.heitorcolangelo.presentation.common.viewbinding.viewBinding
import javax.inject.Inject

class MovieListFragment : Fragment(R.layout.fragment_movie_list), PagedAdapter.PaginationListener {

    @Inject
    lateinit var viewModel: MovieListViewModel
    private val binding: FragmentMovieListBinding by viewBinding()
    private val listAdapter = MovieListAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupRecyclerView()
        setupRefreshView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        inject()
        viewModel.pagedMoves.observe(this, Observer { onMovies(it) })
        viewModel.navigation.observe(this, Observer { onNavigation(it) })
    }

    override fun requestPage() {
        viewModel.getNextPage()
    }

    private fun onNavigation(navigation: MovieListViewModel.Navigation) {
        when (navigation) {
            is MovieListViewModel.Navigation.MovieDetails -> {
                val directions = MovieListFragmentDirections
                    .actionMovieListToMovieDetails(navigation.movieId)
                NavHostFragment.findNavController(this).navigate(directions)
            }
        }
    }

    private fun onMovies(movies: List<MovieItemUiModel>) {
        listAdapter.submitList(movies.toMutableList())
    }

    private fun setupRecyclerView() {
        with(binding.recyclerView) {
            layoutManager = FlexboxLayoutManager(requireContext()).apply {
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.SPACE_EVENLY
            }
            setHasFixedSize(true)
            adapter = listAdapter
        }
    }

    private fun setupAdapter() {
        listAdapter.onItemClicked(viewModel::onItemClicked)
    }

    private fun setupRefreshView() {
        binding.srMovieList.setOnRefreshListener {
            viewModel.onRefresh()
        }
    }
}
