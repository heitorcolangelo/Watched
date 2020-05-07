package com.heitorcolangelo.data.factory

import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.test.common.MockFactory

object MovieDataModelFactory : MockFactory<MovieDataModel> {
    override fun make(): MovieDataModel {
        return MovieDataModel(
            movieId = randomId(),
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
