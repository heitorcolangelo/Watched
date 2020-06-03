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
import com.google.android.material.snackbar.Snackbar
import com.heitorcolangelo.movie.R
import com.heitorcolangelo.movie.databinding.FragmentMovieListBinding
import com.heitorcolangelo.movie.di.inject
import com.heitorcolangelo.movie.model.MovieItemUiModel
import com.heitorcolangelo.presentation.common.list.PagedAdapter
import com.heitorcolangelo.presentation.common.model.PageUiModel
import com.heitorcolangelo.presentation.common.view.ViewState
import com.heitorcolangelo.presentation.common.view.setAction
import com.heitorcolangelo.presentation.common.viewbinding.makeSnackBar
import com.heitorcolangelo.presentation.common.viewbinding.viewBinding
import javax.inject.Inject

class MovieListFragment : Fragment(R.layout.fragment_movie_list), PagedAdapter.PaginationListener {

    @Inject
    lateinit var viewModel: MovieListViewModel
    private val binding: FragmentMovieListBinding by viewBinding()
    private val listAdapter = MovieListAdapter(this)
    private val sbErrorMessage: Snackbar by lazy {
        setupErrorSnackBar()
    }

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
        viewModel.viewState.observe(this, Observer { onViewState(it) })
    }

    override fun requestPage() {
        viewModel.getNextPage()
    }

    private fun onViewState(viewState: ViewState) {
        when (viewState) {
            ViewState.Loading -> {
                binding.srMovieList.isRefreshing = true
                sbErrorMessage.dismiss()
            }
            ViewState.Content -> {
                binding.srMovieList.isRefreshing = false
                sbErrorMessage.dismiss()
            }
            ViewState.Error -> {
                binding.srMovieList.isRefreshing = false
                sbErrorMessage.show()
            }
        }
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

    private fun onMovies(newPage: PageUiModel<MovieItemUiModel>) {
        listAdapter.submitPage(newPage)
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

    private fun setupErrorSnackBar(): Snackbar {
        return binding.makeSnackBar(R.string.message_loading_content_error, Snackbar.LENGTH_LONG)
            .setAction(R.string.action_try_again, viewModel::onTryAgain)
    }
}
