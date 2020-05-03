package com.heitorcolangelo.domain.common.usecase

import com.heitorcolangelo.domain.common.model.DomainModel
import com.heitorcolangelo.domain.common.scheduler.ExecutionThreadProvider
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observers.DisposableObserver

abstract class ObservableUseCase<Model : DomainModel, Args : UseCaseArgs>(
    private val threadProvider: ExecutionThreadProvider
) : DisposableUseCase() {
    abstract fun build(args: Args): Observable<Model>

    fun execute(args: Args, observer: DisposableObserver<Model>) {
        val observable = this.build(args)
            .subscribeOn(threadProvider.io())
            .observeOn(threadProvider.ui())
        disposables.add(observable.subscribeWith(observer))
    }
}
