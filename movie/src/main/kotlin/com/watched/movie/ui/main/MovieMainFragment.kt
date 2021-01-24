package com.watched.movie.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.watched.movie.R
import com.watched.movie.databinding.FragmentMovieMainBinding
import com.watched.movie.databinding.ItemMovieTopXBinding
import com.watched.movie.di.inject
import com.watched.movie.model.TopXMovieUiModel
import com.watched.presentation.common.list.BaseAdapter
import com.watched.presentation.common.viewbinding.viewBinding
import javax.inject.Inject

class MovieMainFragment : Fragment(R.layout.fragment_movie_main) {

    @Inject
    lateinit var viewModel: MovieMainViewModel

    private val binding: FragmentMovieMainBinding by viewBinding()
    private val topXMovieItemBinder =
        object : BaseAdapter.Binder<ItemMovieTopXBinding, TopXMovieUiModel> {
            override fun bindListItem(binding: ItemMovieTopXBinding, model: TopXMovieUiModel) {
                binding.ivPoster.setImageURI(model.posterPath)
                binding.tvTopX.text = getString(R.string.top_x_today, model.position)
            }
        }
    private val topXMovieAdapter = BaseAdapter(ItemMovieTopXBinding::inflate, topXMovieItemBinder)
    private val adapter = ConcatAdapter(topXMovieAdapter)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        inject()

        viewModel.topXMovie.observe(this, ::onTopXMovie)
    }

    private fun onTopXMovie(movie: TopXMovieUiModel) {
        topXMovieAdapter.submitList(listOf(movie))
    }
}
