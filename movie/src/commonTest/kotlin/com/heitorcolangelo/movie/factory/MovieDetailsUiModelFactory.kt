package com.heitorcolangelo.movie.factory

import com.heitorcolangelo.common.test.MockFactory
import com.heitorcolangelo.movie.model.MovieDetailsUiModel
import com.heitorcolangelo.presentation.common.model.FormattedDateUiModel
import com.heitorcolangelo.presentation.common.model.MediaImageUiModel

object MovieDetailsUiModelFactory : MockFactory<MovieDetailsUiModel> {
    override fun make(): MovieDetailsUiModel {
        val dateUiModel = FormattedDateUiModel(randomString())
        val imageUiModel = MediaImageUiModel.Medium(randomString(), randomString())
        return MovieDetailsUiModel(
            randomId(), randomString(), randomString(), dateUiModel, randomFloat(), imageUiModel
        )
    }
}
