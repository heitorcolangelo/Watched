package com.heitorcolangelo.data.remote.factory

import com.heitorcolangelo.data.remote.movie.model.MovieResponseModel
import com.heitorcolangelo.test.common.MockFactory

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