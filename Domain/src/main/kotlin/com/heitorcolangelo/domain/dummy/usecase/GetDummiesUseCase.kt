package com.heitorcolangelo.domain.dummy.usecase

import com.heitorcolangelo.domain.common.usecase.NoArgObservableUseCase
import com.heitorcolangelo.domain.dummy.model.DummyDomainModel
import com.heitorcolangelo.domain.dummy.repository.DummyRepository
import com.heitorcolangelo.domain.scheduler.ExecutionThreadProvider
import io.reactivex.Observable

class GetDummiesUseCase(
    private val repository: DummyRepository,
    threadProvider: ExecutionThreadProvider
) : NoArgObservableUseCase<List<DummyDomainModel>>(threadProvider) {
    override fun build(): Observable<List<DummyDomainModel>> {
        return repository.getDummies()
    }
}
