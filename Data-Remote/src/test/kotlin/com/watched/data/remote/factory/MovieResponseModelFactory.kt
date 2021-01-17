package com.watched.data.remote.factory

import com.watched.common.test.MockFactory
import com.watched.data.remote.movie.model.MovieResponseModel

object MovieResponseModelFactory : MockFactory<MovieResponseModel> {
    override fun make(): MovieResponseModel {
        return MovieResponseModel(
            id = randomLong(),
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
