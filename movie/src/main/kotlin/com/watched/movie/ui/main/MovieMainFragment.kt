package com.watched.movie.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.fragment.app.Fragment
import com.watched.movie.databinding.FragmentMovieMainBinding
import com.watched.movie.di.inject
import com.watched.presentation.components.MediaMainScreen
import com.watched.presentation.media.model.MediaSectionItemUiModel
import com.watched.presentation.media.model.MediaTopXUiModel
import com.watched.presentation.theme.WatchedTheme
import javax.inject.Inject

class MovieMainFragment : Fragment() {

    @Inject
    lateinit var viewModel: MovieMainViewModel

    private lateinit var binding: FragmentMovieMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.composeView.setContent {
            MovieMainScreen(viewModel)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieMainBinding.inflate(inflater, container, false)
        return binding.root
    }
}

@Composable
fun MovieMainScreen(viewModel: MovieMainViewModel) {
    WatchedTheme {
        val topXMovie: MediaTopXUiModel? by viewModel.topXMovie.observeAsState()
        val sections: List<MediaSectionItemUiModel>? by viewModel.sectionMovies.observeAsState()

        Scaffold {
            MediaMainScreen(topXMovie, sections)
        }
    }
}
