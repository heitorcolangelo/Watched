package com.heitorcolangelo.dummy.domain

import com.heitorcolangelo.domain.common.model.PageDomainModel
import com.heitorcolangelo.domain.common.scheduler.ExecutionThreadProvider
import com.heitorcolangelo.domain.common.usecase.NoArgsObservableUseCase
import com.heitorcolangelo.domain.dummy.model.DummyDomainModel
import com.heitorcolangelo.domain.dummy.repository.DummyRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetDummiesUseCase @Inject constructor(
    private val repository: DummyRepository,
    threadProvider: ExecutionThreadProvider
) : NoArgsObservableUseCase<PageDomainModel<DummyDomainModel>>(threadProvider) {
    override fun build(): Observable<PageDomainModel<DummyDomainModel>> {
        return repository.getDummies()
    }
}
