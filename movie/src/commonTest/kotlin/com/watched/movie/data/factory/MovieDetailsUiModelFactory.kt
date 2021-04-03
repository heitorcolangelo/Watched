package com.watched.movie.data.factory

import com.watched.common.test.MockFactory
import com.watched.movie.ui.model.MovieDetailsUiModel
import com.watched.presentation.common.model.FormattedDateUiModel
import com.watched.presentation.media.ui.model.MediaImageUiModel

object MovieDetailsUiModelFactory : MockFactory<MovieDetailsUiModel> {
    override fun make(): MovieDetailsUiModel {
        return MovieDetailsUiModel(
            id = randomId(),
            title = randomString(),
            overview = randomString(),
            releaseDate = FormattedDateUiModel(randomString()),
            voteAverage = randomFloat(),
            backdrop = MediaImageUiModel(randomString(), randomString())
        )
    }
}
