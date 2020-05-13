package com.heitorcolangelo.movie.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.heitorcolangelo.movie.R
import com.heitorcolangelo.movie.databinding.FragmentMovieDetailsBinding
import com.heitorcolangelo.movie.di.inject
import com.heitorcolangelo.movie.model.MovieDetailsUiModel
import com.heitorcolangelo.movie.ui.MovieActivity
import com.heitorcolangelo.presentation.common.view.binding.viewBinding
import javax.inject.Inject

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {
    companion object {
        private const val MOVIE_ID_KEY = "MOVIE_ID_KEY"

        fun newInstance(movieId: String): MovieDetailsFragment {
            val bundle = Bundle()
            bundle.putString(MOVIE_ID_KEY, movieId)
            val fragment = MovieDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }

        private fun getMovieId(arguments: Bundle?) = arguments?.getString(MOVIE_ID_KEY)
    }

    @Inject
    lateinit var viewModel: MovieDetailsViewModel
    private val binding: FragmentMovieDetailsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with((requireActivity() as MovieActivity)) {
            toolbar.isVisible = false
            window.statusBarColor = ResourcesCompat.getColor(
                resources,
                android.R.color.transparent,
                theme
            )
        }
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        inject()
        val movieId = getMovieId(arguments)
        viewModel.setMovieId(movieId)
        viewModel.onViewReady()
        viewModel.movie.observe(this, Observer { onMovie(it) })
    }

    private fun onMovie(model: MovieDetailsUiModel) = with(binding) {
        collapsingToolbar.title = model.title

        Glide.with(this@MovieDetailsFragment)
            .load(model.backdropPath.getFullUrl())
            .into(ivMovieBackdrop)

        with(movieDetailsContent) {
            tvOverviewContent.text = model.overview
            tvReleaseDate.text = getString(
                R.string.movie_details_release_date,
                model.releaseDate.formattedDate
            )
        }
    }
}
