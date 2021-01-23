package com.watched.movie.domain

import com.watched.domain.common.usecase.NoArgUseCase
import com.watched.domain.movie.model.SortOptionsDomainModel
import com.watched.domain.movie.model.TopXMovieDomainModel
import com.watched.domain.movie.repository.MovieRepository
import javax.inject.Inject
import kotlin.random.Random

class GetTopXPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) : NoArgUseCase<TopXMovieDomainModel>() {
    override suspend fun invoke(): TopXMovieDomainModel {
        val nextMovieIndex = Random.nextInt(RANGE_START, RANGE_END)
        val movies = repository.getMovies(SortOptionsDomainModel.Popularity)

        return TopXMovieDomainModel(nextMovieIndex, movies[nextMovieIndex])
    }

    companion object {
        const val RANGE_START = 0
        const val RANGE_END = 9
    }
}
