package com.heitorcolangelo.domain.dummy.usecase

import com.heitorcolangelo.domain.dummy.repository.DummyRepository
import com.heitorcolangelo.domain.factory.DummyFactory
import com.heitorcolangelo.domain.scheduler.ExecutionThreadProvider
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Test

class GetDummiesUseCaseTest {

    private val repository: DummyRepository = mockk(relaxed = true)
    private val threadProvider: ExecutionThreadProvider = mockk(relaxed = true)

    private val useCase = GetDummiesUseCase(repository, threadProvider)

    @Test
    fun `WHEN build THEN return repository dummies`() {
        val dummyList = DummyFactory.makeList(3)
        every { repository.getDummies() } returns Observable.just(dummyList)

        val testObservable = useCase.build().test()

        testObservable.assertValue(dummyList)
        verify { repository.getDummies() }
    }
}
