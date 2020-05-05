package com.heitorcolangelo.domain.dummy.usecase

import com.heitorcolangelo.domain.common.model.PageDomainModel
import com.heitorcolangelo.domain.common.scheduler.ExecutionThreadProvider
import com.heitorcolangelo.domain.dummy.repository.DummyRepository
import com.heitorcolangelo.domain.factory.DummyDomainModelFactory
import com.heitorcolangelo.dummy.domain.GetDummiesUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class GetDummiesUseCaseTest {

    private val repository: DummyRepository = mockk(relaxed = true)
    private val threadProvider: ExecutionThreadProvider = mockk(relaxed = true)
    private val useCase = GetDummiesUseCase(repository, threadProvider)

    @Test
    fun `WHEN build THEN return repository dummies`() {
        val dummyPage = PageDomainModel(items = DummyDomainModelFactory.makeList(3))
        every { repository.getDummies() } returns Observable.just(dummyPage)

        val testObservable = useCase.build().test()

        testObservable.assertValue(dummyPage)
        verify { repository.getDummies() }
    }
}
