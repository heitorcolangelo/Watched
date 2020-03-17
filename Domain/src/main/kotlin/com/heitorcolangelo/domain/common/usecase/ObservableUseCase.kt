package com.heitorcolangelo.domain.common.usecase

import com.heitorcolangelo.domain.common.model.DomainModel
import com.heitorcolangelo.domain.common.scheduler.ExecutionThreadProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

abstract class ObservableUseCase<Model : DomainModel, Args : UseCaseArgs>(
    private val threadProvider: ExecutionThreadProvider
) {
    private val disposables = CompositeDisposable()

    protected abstract fun build(args: Args): Observable<Model>

    open fun execute(observer: DisposableObserver<Model>, args: Args) {
        val observable = this.build(args)
            .subscribeOn(threadProvider.io())
            .observeOn(threadProvider.ui())
        disposables.add(observable.subscribeWith(observer))
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }
}
