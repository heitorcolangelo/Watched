package com.heitorcolangelo.domain.common.usecase

import com.heitorcolangelo.domain.common.scheduler.ExecutionThreadProvider
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver

abstract class CompletableUseCase<Args : UseCaseArgs>(
    private val threadProvider: ExecutionThreadProvider
) {
    private val disposables = CompositeDisposable()

    protected abstract fun build(args: Args): Completable

    open fun execute(observer: DisposableCompletableObserver, args: Args) {
        val observable = this.build(args)
            .subscribeOn(threadProvider.io())
            .observeOn(threadProvider.ui())
        disposables.add(observable.subscribeWith(observer))
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }
}
