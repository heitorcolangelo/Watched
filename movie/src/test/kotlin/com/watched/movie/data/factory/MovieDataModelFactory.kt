package com.watched.movie.data.factory

import com.watched.common.test.MockFactory
import com.watched.movie.data.model.MovieDataModel

object MovieDataModelFactory : MockFactory<MovieDataModel> {
    override fun make(): MovieDataModel {
        return MovieDataModel(
            id = randomId(),
            title = randomString(),
            overview = randomString(),
            backdropPath = randomString(),
            posterPath = randomString(),
            voteAverage = randomFloat(),
            popularity = randomFloat(),
            releaseDate = randomString()
        )
    }
}
