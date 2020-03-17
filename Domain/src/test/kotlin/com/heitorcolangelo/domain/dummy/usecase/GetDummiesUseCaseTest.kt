package com.heitorcolangelo.domain.dummy.usecase

import com.heitorcolangelo.domain.common.scheduler.ExecutionThreadProvider
import com.heitorcolangelo.domain.dummy.repository.DummyRepository
import com.heitorcolangelo.domain.factory.DummiesFactory
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
        val dummies = DummiesFactory.make()
        every { repository.getDummies() } returns Observable.just(dummies)

        val testObservable = useCase.build().test()

        testObservable.assertValue(dummies)
        verify { repository.getDummies() }
    }
}
