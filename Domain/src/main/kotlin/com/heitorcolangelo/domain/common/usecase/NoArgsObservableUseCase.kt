package com.heitorcolangelo.domain.common.usecase

import com.heitorcolangelo.domain.common.model.DomainModel
import com.heitorcolangelo.domain.scheduler.ExecutionThreadProvider
import io.reactivex.Observable

abstract class NoArgsObservableUseCase<Model : DomainModel>(
    executionThreadProvider: ExecutionThreadProvider
) : ObservableUseCase<Model, UseCaseArgs>(executionThreadProvider) {
    abstract fun build(): Observable<Model>

    override fun build(args: UseCaseArgs): Observable<Model> {
        return build()
    }
}
