package com.heitorcolangelo.movie.factory

import com.heitorcolangelo.common.test.MockFactory
import com.heitorcolangelo.movie.model.MovieItemUiModel

object MovieItemUiModelFactory : MockFactory<MovieItemUiModel> {
    override fun make(): MovieItemUiModel {
        return MovieItemUiModel(randomId(), randomString())
    }
}