package com.heitorcolangelo.movie.ui.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.snackbar.Snackbar
import com.heitorcolangelo.movie.R
import com.heitorcolangelo.movie.databinding.FragmentMovieListBinding
import com.heitorcolangelo.movie.databinding.ItemMovieBinding
import com.heitorcolangelo.movie.di.inject
import com.heitorcolangelo.movie.model.MovieItemUiModel
import com.heitorcolangelo.presentation.common.list.BasePagingAdapter
import com.heitorcolangelo.presentation.common.navigation.navigateSafelyWithAnimation
import com.heitorcolangelo.presentation.common.view.ViewState
import com.heitorcolangelo.presentation.common.view.Visibility
import com.heitorcolangelo.presentation.common.view.visibility
import com.heitorcolangelo.presentation.common.viewbinding.makeSnackBar
import com.heitorcolangelo.presentation.common.viewbinding.viewBinding
import javax.inject.Inject

class MovieListFragment :
    Fragment(R.layout.fragment_movie_list),
    BasePagingAdapter.Binder<ItemMovieBinding, MovieItemUiModel> {

    @Inject
    lateinit var viewModel: MovieListViewModel
    private val binding: FragmentMovieListBinding by viewBinding()
    private val sbErrorMessage: Snackbar by lazy { setupErrorSnackBar() }
    private val listAdapter = BasePagingAdapter(ItemMovieBinding::inflate, this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupRecyclerView()
        setupRefreshView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        inject()
        viewModel.viewState.observe(this, ::onViewState)
        viewModel.navigation.observe(this, ::navigateSafelyWithAnimation)
        viewModel.getMoviePage(listAdapter::submitData)

    }

    override fun bindListItem(binding: ItemMovieBinding, model: MovieItemUiModel?) {
        binding.ivMoviePoster.setImageURI(model?.posterPath)
    }

    private fun onViewState(viewState: ViewState) {
        binding.srMovieList.isRefreshing(viewState.loadingVisibility)
        binding.recyclerView.visibility(viewState.contentVisibility)
        sbErrorMessage.visibility(viewState.errorVisibility)
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
            listAdapter.refresh()
            viewModel.onRefresh()
            viewModel.getMoviePage(listAdapter::submitData)
        }
    }

    private fun setupErrorSnackBar(): Snackbar {
        return binding.makeSnackBar(R.string.message_loading_content_error, Snackbar.LENGTH_LONG)
            .setAction(R.string.action_try_again) {
                listAdapter.retry()
            }
    }

    private fun SwipeRefreshLayout.isRefreshing(visibility: Visibility) {
        isRefreshing = visibility == Visibility.Visible
    }
}
