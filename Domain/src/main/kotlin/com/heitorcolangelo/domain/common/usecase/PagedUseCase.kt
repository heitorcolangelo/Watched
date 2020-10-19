package com.heitorcolangelo.domain.common.usecase

import com.heitorcolangelo.domain.common.model.DomainModel
import com.heitorcolangelo.domain.common.model.PageDomainModel

abstract class PagedUseCase<Model : DomainModel> {
    companion object {
        const val NO_PAGE_LOADED = -1
        const val PAGE_INCREMENT = 1
    }

    private var currentPage: Int = NO_PAGE_LOADED

    suspend fun get(args: Args): PageDomainModel<Model> {
        return invoke(args).also {
            currentPage++
        }
    }

    protected abstract suspend operator fun invoke(args: Args): PageDomainModel<Model>

    /**
     * Returns the value of the next page to be fetched based on [Args.forceRefresh] value.
     * @return [PageDomainModel.FIRST_PAGE] if [Args.forceRefresh] is true,
     * @return [currentPage] + 1 if [Args.forceRefresh] is false.
     */
    protected fun nextPage(args: Args): Int {
        if (args.forceRefresh) {
            currentPage = NO_PAGE_LOADED
        }

        return currentPage + PAGE_INCREMENT
    }

    data class Args(val forceRefresh: Boolean = false) : UseCaseArgs
}
