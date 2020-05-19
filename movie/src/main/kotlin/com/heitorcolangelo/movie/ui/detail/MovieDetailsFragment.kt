package com.heitorcolangelo.movie.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.heitorcolangelo.movie.R
import com.heitorcolangelo.movie.databinding.FragmentMovieDetailsBinding
import com.heitorcolangelo.movie.di.inject
import com.heitorcolangelo.movie.model.MovieDetailsUiModel
import com.heitorcolangelo.presentation.common.view.binding.viewBinding
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

        with(viewModel) {
            setMovieId(args.movieId)
            movie.observe(this@MovieDetailsFragment, Observer { onMovie(it) })
            navigation.observe(this@MovieDetailsFragment, Observer { onNavigation(it) })

            onViewReady()
        }
    }

    private fun onNavigation(navigation: MovieDetailsViewModel.Navigation) {
        when (navigation) {
            MovieDetailsViewModel.Navigation.Back -> requireActivity().onBackPressed()
        }
    }

    private fun onMovie(model: MovieDetailsUiModel) = with(binding) {
        collapsingToolbar.title = model.title

        Glide.with(this@MovieDetailsFragment)
            .load(model.backdropPath)
            .into(ivMovieBackdrop)

        with(movieDetailsContent) {
            tvOverviewContent.text = model.overview
            tvReleaseDate.text = getString(
                R.string.movie_details_release_date,
                model.releaseDate.formattedDate
            )
        }
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            viewModel.onBackPressed()
        }
    }
}
