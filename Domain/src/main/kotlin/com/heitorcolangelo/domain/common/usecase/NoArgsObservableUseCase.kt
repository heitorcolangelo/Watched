package com.heitorcolangelo.domain.common.usecase

import com.heitorcolangelo.domain.common.model.DomainModel
import com.heitorcolangelo.domain.common.scheduler.ExecutionThreadProvider
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observers.DisposableObserver

abstract class NoArgsObservableUseCase<Model : DomainModel>(
    private val threadProvider: ExecutionThreadProvider
) : DisposableUseCase() {
    abstract fun build(): Observable<Model>

    fun execute(observer: DisposableObserver<Model>) {
        val observable = this.build()
            .subscribeOn(threadProvider.io())
            .observeOn(threadProvider.ui())
        disposables.add(observable.subscribeWith(observer))
    }
}
