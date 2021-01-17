package com.watched.movie.factory

import com.watched.common.test.MockFactory
import com.watched.movie.model.MovieItemUiModel

object MovieItemUiModelFactory : MockFactory<MovieItemUiModel> {
    override fun make(): MovieItemUiModel {
        return MovieItemUiModel(randomId(), randomString())
    }
}
