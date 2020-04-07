package com.heitorcolangelo.domain.common.usecase

import com.heitorcolangelo.domain.common.scheduler.ExecutionThreadProvider
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.observers.DisposableCompletableObserver

abstract class NoArgsCompletableUseCase(
    private val threadProvider: ExecutionThreadProvider
) : DisposableUseCase() {
    protected abstract fun build(): Completable

    fun execute(observer: DisposableCompletableObserver) {
        val observable = this.build()
            .subscribeOn(threadProvider.io())
            .observeOn(threadProvider.ui())
        disposables.add(observable.subscribeWith(observer))
    }
}
