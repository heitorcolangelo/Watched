package com.heitorcolangelo.domain.common.usecase

import io.reactivex.rxjava3.disposables.CompositeDisposable

open class DisposableUseCase {
    protected val disposables = CompositeDisposable()

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }
}
