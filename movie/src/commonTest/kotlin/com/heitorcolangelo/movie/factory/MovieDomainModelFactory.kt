package com.heitorcolangelo.movie.factory

import com.heitorcolangelo.common.test.MockFactory
import com.heitorcolangelo.domain.common.model.RawDateDomainModel
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.domain.movie.model.MovieImageDomainModel

object MovieDomainModelFactory : MockFactory<MovieDomainModel> {
    override fun make(): MovieDomainModel {
        return MovieDomainModel(
            movieId = randomId(),
            title = randomString(),
            overview = randomString(),
            backdrop = MovieImageDomainModel(randomString()),
            poster = MovieImageDomainModel(randomString()),
            voteAverage = randomFloat(),
            popularity = randomFloat(),
            releaseDate = RawDateDomainModel(randomString())
        )
    }
}
