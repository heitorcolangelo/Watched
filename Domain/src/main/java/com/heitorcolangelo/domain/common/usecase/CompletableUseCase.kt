package com.heitorcolangelo.domain.common.usecase

import com.heitorcolangelo.domain.scheduler.ExecutionThreadProvider
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver

abstract class CompletableUseCase<in Params>(
    private val threadProvider: ExecutionThreadProvider
) {
    private val disposables = CompositeDisposable()

    protected abstract fun build(params: Params): Completable

    open fun execute(observer: DisposableCompletableObserver, params: Params) {
        val observable = this.build(params)
            .subscribeOn(threadProvider.io())
            .observeOn(threadProvider.ui())
        disposables.add(observable.subscribeWith(observer))
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }
}