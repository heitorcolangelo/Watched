package com.watched.movie.factory

import com.watched.common.test.MockFactory
import com.watched.movie.model.MovieDetailsUiModel
import com.watched.presentation.common.model.FormattedDateUiModel
import com.watched.presentation.common.model.MovieImageUiModel

object MovieDetailsUiModelFactory : MockFactory<MovieDetailsUiModel> {
    override fun make(): MovieDetailsUiModel {
        val dateUiModel = FormattedDateUiModel(randomString())
        val imageUrl = MovieImageUiModel(randomString(), randomString())
            .getUrl(MovieImageUiModel.Size.MEDIUM)
        return MovieDetailsUiModel(
            randomId(), randomString(), randomString(), dateUiModel, randomFloat(), imageUrl
        )
    }
}
