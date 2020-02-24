package com.heitorcolangelo.domain.common.usecase

import com.heitorcolangelo.domain.scheduler.ExecutionThreadProvider
import io.reactivex.Observable

abstract class NoArgObservableUseCase<T>(
    executionThreadProvider: ExecutionThreadProvider
) : ObservableUseCase<T, Nothing>(executionThreadProvider) {
    abstract fun build(): Observable<T>

    override fun build(params: Nothing): Observable<T> {
        return build()
    }
}
