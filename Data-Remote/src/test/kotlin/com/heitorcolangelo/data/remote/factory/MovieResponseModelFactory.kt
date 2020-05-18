package com.heitorcolangelo.data.remote.factory

import com.heitorcolangelo.common.test.MockFactory
import com.heitorcolangelo.data.remote.movie.model.MovieResponseModel

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
