package com.heitorcolangelo.domain.common.usecase

import androidx.paging.PagingData
import com.heitorcolangelo.domain.common.model.DomainModel
import kotlinx.coroutines.flow.Flow

abstract class PagedUseCase<Model : DomainModel> {
    suspend fun get(args: Args): Flow<PagingData<Model>> {
        return invoke(args)
    }

    protected abstract suspend operator fun invoke(args: Args): Flow<PagingData<Model>>

    data class Args(val forceRefresh: Boolean = false) : UseCaseArgs
}
