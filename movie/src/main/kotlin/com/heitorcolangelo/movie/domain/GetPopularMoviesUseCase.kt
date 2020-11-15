package com.heitorcolangelo.movie.domain

import androidx.paging.PagingData
import com.heitorcolangelo.domain.common.usecase.PagedUseCase
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.domain.movie.model.MoviesSortOption
import com.heitorcolangelo.domain.movie.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) : PagedUseCase<MovieDomainModel>() {
    override suspend fun invoke(args: Args): Flow<PagingData<MovieDomainModel>> {
        return repository.getMovies(MoviesSortOption.Popularity, args.forceRefresh)
    }
}
