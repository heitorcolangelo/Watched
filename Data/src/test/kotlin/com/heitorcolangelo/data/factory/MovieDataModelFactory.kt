package com.heitorcolangelo.data.factory

import com.heitorcolangelo.data.movie.model.MovieDataModel

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
            releaseDate = randomDate()
        )
    }
}