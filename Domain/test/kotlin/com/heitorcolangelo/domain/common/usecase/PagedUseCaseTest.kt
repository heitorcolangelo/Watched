package com.heitorcolangelo.domain.common.usecase

import com.heitorcolangelo.domain.common.model.DomainModel
import com.heitorcolangelo.domain.common.model.PageDomainModel
import com.heitorcolangelo.domain.common.scheduler.ExecutionThreadProvider
import com.heitorcolangelo.domain.common.scheduler.PagedObserver
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.internal.schedulers.TrampolineScheduler
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.exp

class PagedUseCaseTest {

    private val scheduler = TrampolineScheduler.instance()

    private val threadProvider = object : ExecutionThreadProvider {
        override fun io(): Scheduler {
            return scheduler
        }

        override fun ui(): Scheduler {
            return scheduler
        }
    }

    @Test
    fun `WHEN loaded page is before current page THEN invalidate pages`() {
        val returnedPage = PageDomainModel<DomainModel>(page = PagedUseCase.NO_PAGE_LOADED - 1)
        val useCase = TestPagedUseCase(returnedPage, threadProvider)
        val observer = mockk<PagedObserver<DomainModel>>(relaxed = true)

        useCase.execute(PagedUseCase.Args(), observer)

        verify { observer.invalidatePages() }
    }

    @Test
    fun `WHEN return new page THEN currentPage is returned page`() {
        val returnedPageIndex = PagedUseCase.NO_PAGE_LOADED + 1
        val returnedPage = PageDomainModel<DomainModel>(page = returnedPageIndex)
        val useCase = TestPagedUseCase(returnedPage, threadProvider)
        val observer = mockk<PagedObserver<DomainModel>>(relaxed = true)

        useCase.execute(PagedUseCase.Args(), observer)

        val expectedPage = returnedPageIndex + 1
        assertEquals(expectedPage, useCase.nextPageIndex())
    }

    @Test
    fun `WHEN force refresh THEN reset current page`() {
        val returnedPageIndex = 3
        val returnedPage = PageDomainModel<DomainModel>(page = returnedPageIndex)
        val useCase = TestPagedUseCase(returnedPage, threadProvider)
        useCase.execute(PagedUseCase.Args(), mockk(relaxed = true))

        val nextPageIndex = useCase.nextPageIndex(true)

        val expectedPage = PagedUseCase.NO_PAGE_LOADED + 1
        assertEquals(expectedPage, nextPageIndex)
    }

    @Test
    fun `WHEN next page THEN return current page plus one`() {
        val returnedPage = PageDomainModel<DomainModel>()
        val useCase = TestPagedUseCase(returnedPage, threadProvider)

        val nextPageIndex = useCase.nextPageIndex()

        val expectedPage = PagedUseCase.NO_PAGE_LOADED + 1
        assertEquals(expectedPage, nextPageIndex)
    }

    private class TestPagedUseCase(
        private val returnedPage: PageDomainModel<DomainModel>,
        threadProvider: ExecutionThreadProvider
    ) : PagedUseCase<DomainModel>(threadProvider) {
        override fun build(args: Args): Observable<PageDomainModel<DomainModel>> {
            return Observable.just(returnedPage)
        }

        fun nextPageIndex(forceRefresh: Boolean = false) = nextPage(Args(forceRefresh))
    }

}