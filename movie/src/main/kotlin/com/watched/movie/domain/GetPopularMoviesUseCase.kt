package com.watched.movie.domain

import androidx.paging.PagingData
import com.watched.domain.common.usecase.PagedUseCase
import com.watched.domain.movie.model.MovieDomainModel
import com.watched.domain.movie.model.MoviesSortOption
import com.watched.domain.movie.repository.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) : PagedUseCase<MovieDomainModel>() {
    override suspend fun invoke(args: Args): Flow<PagingData<MovieDomainModel>> {
        return repository.getMovies(MoviesSortOption.Popularity, args.forceRefresh)
    }
}
