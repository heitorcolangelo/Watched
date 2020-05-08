package com.heitorcolangelo.movie.factory

import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.test.common.MockFactory

object MovieDomainModelFactory : MockFactory<MovieDomainModel> {
    override fun make(): MovieDomainModel {
        return MovieDomainModel(
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