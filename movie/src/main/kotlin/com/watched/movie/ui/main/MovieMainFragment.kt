package com.watched.movie.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.watched.movie.R
import com.watched.movie.databinding.FragmentMovieMainBinding
import com.watched.movie.databinding.ItemMovieLatestBinding
import com.watched.movie.di.inject
import com.watched.movie.model.LatestMovieUiModel
import com.watched.presentation.common.list.BaseAdapter
import com.watched.presentation.common.viewbinding.viewBinding
import javax.inject.Inject

class MovieMainFragment :
    Fragment(R.layout.fragment_movie_main),
    BaseAdapter.Binder<ItemMovieLatestBinding, LatestMovieUiModel> {

    @Inject
    lateinit var viewModel: MovieMainViewModel

    private val binding: FragmentMovieMainBinding by viewBinding()
    private val latestAdapter = BaseAdapter(ItemMovieLatestBinding::inflate, this)
    private val adapter = ConcatAdapter(latestAdapter)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        inject()

        viewModel.latestMovie.observe(this, ::onLatestMovie)
    }

    override fun bindListItem(binding: ItemMovieLatestBinding, model: LatestMovieUiModel) {
        binding.ivPoster.setImageURI(model.posterPath)
    }

    private fun onLatestMovie(movie: LatestMovieUiModel) {
        latestAdapter.submitList(listOf(movie))
    }
}