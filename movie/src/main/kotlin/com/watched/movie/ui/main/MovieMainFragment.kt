package com.watched.movie.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.watched.databinding.ItemMediaSectionBinding
import com.watched.databinding.ItemMediaTopXBinding
import com.watched.movie.R
import com.watched.movie.databinding.FragmentMovieMainBinding
import com.watched.movie.di.inject
import com.watched.presentation.common.list.BaseAdapter
import com.watched.presentation.common.viewbinding.viewBinding
import com.watched.presentation.media.MediaSectionItemBinder
import com.watched.presentation.media.MediaTopXItemBinder
import com.watched.presentation.media.model.MediaSectionItemUiModel
import com.watched.presentation.media.model.MediaTopXUiModel
import javax.inject.Inject

class MovieMainFragment : Fragment(R.layout.fragment_movie_main) {

    @Inject
    lateinit var viewModel: MovieMainViewModel

    private val binding: FragmentMovieMainBinding by viewBinding()
    private val topXAdapter = BaseAdapter(ItemMediaTopXBinding::inflate, MediaTopXItemBinder())
    private val sectionsAdapter = BaseAdapter(
        ItemMediaSectionBinding::inflate,
        MediaSectionItemBinder()
    )
    private val mainAdapter = ConcatAdapter(topXAdapter, sectionsAdapter)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = mainAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        inject()

        viewModel.topXMovie.observe(this, ::onTopXMovie)
        viewModel.sectionMovies.observe(this, ::onSectionMovies)
    }

    private fun onTopXMovie(movie: MediaTopXUiModel) {
        topXAdapter.submitList(listOf(movie))
    }

    private fun onSectionMovies(sections: List<MediaSectionItemUiModel?>) {
        sectionsAdapter.submitList(sections)
    }
}
