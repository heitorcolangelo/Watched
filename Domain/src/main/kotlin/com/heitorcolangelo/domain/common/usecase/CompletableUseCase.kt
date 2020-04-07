package com.heitorcolangelo.domain.common.usecase

import com.heitorcolangelo.domain.common.scheduler.ExecutionThreadProvider
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableCompletableObserver

abstract class CompletableUseCase<Args : UseCaseArgs>(
    private val threadProvider: ExecutionThreadProvider
): DisposableUseCase() {
    protected abstract fun build(args: Args): Completable

    fun execute(args: Args, observer: DisposableCompletableObserver) {
        val observable = this.build(args)
            .subscribeOn(threadProvider.io())
            .observeOn(threadProvider.ui())
        disposables.add(observable.subscribeWith(observer))
    }
}
