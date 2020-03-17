package com.heitorcolangelo.domain.common.usecase

import com.heitorcolangelo.domain.common.scheduler.ExecutionThreadProvider
import io.reactivex.Completable

abstract class NoArgsCompletableUseCase(
    threadProvider: ExecutionThreadProvider
) : CompletableUseCase<UseCaseArgs>(threadProvider) {
    protected abstract fun build(): Completable

    override fun build(args: UseCaseArgs): Completable {
        return build()
    }
}
