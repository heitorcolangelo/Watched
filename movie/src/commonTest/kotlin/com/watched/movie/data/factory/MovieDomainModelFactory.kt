package com.watched.movie.data.factory

import com.watched.common.test.MockFactory
import com.watched.domain.common.model.RawDateDomainModel
import com.watched.domain.media.MediaImageDomainModel
import com.watched.movie.domain.model.MovieDomainModel

object MovieDomainModelFactory : MockFactory<MovieDomainModel> {
    override fun make(): MovieDomainModel {
        return MovieDomainModel(
            id = randomId(),
            title = randomString(),
            overview = randomString(),
            backdrop = MediaImageDomainModel(randomString()),
            poster = MediaImageDomainModel(randomString()),
            voteAverage = randomFloat(),
            popularity = randomFloat(),
            releaseDate = RawDateDomainModel(randomString())
        )
    }
}
