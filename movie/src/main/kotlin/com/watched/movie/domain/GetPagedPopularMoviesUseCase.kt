package com.watched.movie.domain

import androidx.paging.PagingData
import com.watched.domain.common.usecase.PagedUseCase
import com.watched.domain.movie.model.MovieDomainModel
import com.watched.domain.movie.model.SortOptionsDomainModel
import com.watched.domain.movie.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPagedPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) : PagedUseCase<MovieDomainModel>() {
    override suspend fun invoke(args: Args): Flow<PagingData<MovieDomainModel>> {
        return repository.getPagedMovies(SortOptionsDomainModel.Popularity, args.forceRefresh)
    }
}
