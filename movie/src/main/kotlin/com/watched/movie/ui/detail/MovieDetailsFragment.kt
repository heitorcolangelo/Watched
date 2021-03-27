package com.watched.movie.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.watched.movie.R
import com.watched.movie.databinding.FragmentMovieDetailsBinding
import com.watched.movie.di.inject
import com.watched.movie.ui.model.MovieDetailsUiModel
import com.watched.presentation.common.viewbinding.viewBinding
import javax.inject.Inject

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    @Inject
    lateinit var viewModel: MovieDetailsViewModel
    private val binding: FragmentMovieDetailsBinding by viewBinding()
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()

        viewModel.setMovieId(args.movieId)
        viewModel.movie.observe(this, ::onMovie)
    }

    private fun onMovie(model: MovieDetailsUiModel) = with(binding) {
        collapsingToolbar.title = model.title
        ivMovieBackdrop.setImageURI(model.backdropPath)

        movieDetailsContent.tvOverviewContent.text = model.overview
        movieDetailsContent.tvReleaseDate.text = getString(
            R.string.movie_details_release_date,
            model.releaseDate.formattedDate
        )
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}
