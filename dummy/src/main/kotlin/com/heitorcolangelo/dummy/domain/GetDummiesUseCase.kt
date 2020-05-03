package com.heitorcolangelo.dummy.domain

import com.heitorcolangelo.domain.common.scheduler.ExecutionThreadProvider
import com.heitorcolangelo.domain.common.usecase.NoArgsObservableUseCase
import com.heitorcolangelo.domain.dummy.model.DummiesDomainModel
import com.heitorcolangelo.domain.dummy.repository.DummyRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetDummiesUseCase @Inject constructor(
    private val repository: DummyRepository,
    threadProvider: ExecutionThreadProvider
) : NoArgsObservableUseCase<DummiesDomainModel>(threadProvider) {
    override fun build(): Observable<DummiesDomainModel> {
        return repository.getDummies()
    }
}
