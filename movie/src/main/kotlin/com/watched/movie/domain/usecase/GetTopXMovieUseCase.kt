package com.watched.movie.domain.usecase

import com.watched.domain.common.model.SortOptionsDomainModel
import com.watched.domain.common.usecase.NoArgUseCase
import com.watched.movie.domain.MovieRepository
import com.watched.presentation.media.domain.MediaTopXDomainModel
import javax.inject.Inject
import kotlin.random.Random

class GetTopXMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) : NoArgUseCase<MediaTopXDomainModel>() {
    override suspend fun invoke(): MediaTopXDomainModel {
        val nextMovieIndex = Random.nextInt(RANGE_START, RANGE_END)
        val movies = repository.getMovies(sortOption = SortOptionsDomainModel.Popularity)

        return MediaTopXDomainModel(nextMovieIndex, movies[nextMovieIndex])
    }

    companion object {
        const val RANGE_START = 0
        const val RANGE_END = 9
    }
}
