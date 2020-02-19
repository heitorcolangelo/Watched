package com.heitorcolangelo.domain.common.usecase

import com.heitorcolangelo.domain.scheduler.ExecutionThreadProvider
import io.reactivex.Completable

abstract class NoArgCompletableUseCase(
    threadProvider: ExecutionThreadProvider
) : CompletableUseCase<Nothing>(threadProvider) {
    protected abstract fun build(): Completable

    override fun build(params: Nothing): Completable {
        return build()
    }
}