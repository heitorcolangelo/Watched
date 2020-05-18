package com.heitorcolangelo.data.local.factory

import com.heitorcolangelo.common.test.MockFactory
import com.heitorcolangelo.data.local.movie.entity.MovieEntity

object MovieEntityFactory : MockFactory<MovieEntity> {
    override fun make(): MovieEntity {
        return MovieEntity(
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
