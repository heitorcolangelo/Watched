package com.heitorcolangelo.domain.common.usecase

import com.heitorcolangelo.domain.scheduler.ExecutionThreadProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

abstract class ObservableUseCase<T, in Params>(
    private val threadProvider: ExecutionThreadProvider
) {
    private val disposables = CompositeDisposable()

    protected abstract fun build(params: Params): Observable<T>

    open fun execute(observer: DisposableObserver<T>, params: Params) {
        val observable = this.build(params)
            .subscribeOn(threadProvider.io())
            .observeOn(threadProvider.ui())
        disposables.add(observable.subscribeWith(observer))
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }
}
