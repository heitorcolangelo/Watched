package com.watched.movie.factory

import com.watched.common.test.MockFactory
import com.watched.domain.common.model.RawDateDomainModel
import com.watched.domain.movie.model.MovieDomainModel
import com.watched.domain.movie.model.MovieImageDomainModel

object MovieDomainModelFactory : MockFactory<MovieDomainModel> {
    override fun make(): MovieDomainModel {
        return MovieDomainModel(
            id = randomId(),
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
