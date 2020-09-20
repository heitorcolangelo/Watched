package com.heitorcolangelo.domain.common.usecase

import com.heitorcolangelo.domain.common.model.DomainModel
import com.heitorcolangelo.domain.common.model.PageDomainModel
import com.heitorcolangelo.domain.common.scheduler.ExecutionThreadProvider
import com.heitorcolangelo.domain.common.scheduler.PagedObserver
import io.reactivex.rxjava3.core.Observable

abstract class PagedUseCase<Model : DomainModel>(
    private val threadProvider: ExecutionThreadProvider
) : DisposableUseCase() {
    companion object {
        const val NO_PAGE_LOADED = -1
        const val PAGE_INCREMENT = 1
    }

    private var currentPage: Int = NO_PAGE_LOADED

    abstract fun build(args: Args): Observable<PageDomainModel<Model>>

    fun execute(args: Args, observer: PagedObserver<Model>) {
        val observable = this.build(args)
            .subscribeOn(threadProvider.io())
            .observeOn(threadProvider.ui())
            .doOnNext {
                currentPage = it.page
            }
        disposables.add(observable.subscribeWith(observer))
    }

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
