package com.heitorcolangelo.domain.usecase

import com.heitorcolangelo.domain.scheduler.ObservableProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class ObservableUseCase<T, in Params> constructor(
    private val observableProvider: ObservableProvider
) {
    private val disposables = CompositeDisposable()

    protected abstract fun build(params: Params? = null): Observable<T>

    open fun execute(observer: DisposableObserver<T>, params: Params? = null) {
        val observable = this.build(params)
            .subscribeOn(Schedulers.io())
            .observeOn(observableProvider.scheduler)
        disposables.add(observable.subscribeWith(observer))
    }

    fun dispose() {
        disposables.dispose()
    }
}