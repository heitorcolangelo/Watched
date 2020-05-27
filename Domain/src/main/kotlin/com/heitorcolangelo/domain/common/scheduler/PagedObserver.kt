package com.heitorcolangelo.domain.common.scheduler

import com.heitorcolangelo.domain.common.model.DomainModel
import com.heitorcolangelo.domain.common.model.PageDomainModel
import io.reactivex.rxjava3.observers.DisposableObserver

abstract class PagedObserver<Model : DomainModel> :
    DisposableObserver<PageDomainModel<Model>>() {

    override fun onComplete() {
    }

    override fun onNext(newPage: PageDomainModel<Model>) {
        onLoadPageSuccess(newPage)
    }

    override fun onError(error: Throwable) {
        onLoadPageFailure(error)
    }

    abstract fun onLoadPageSuccess(newPage: PageDomainModel<Model>)
    abstract fun onLoadPageFailure(error: Throwable)

    /**
     * Implement this method to handle the case when you request a new page but the current cache
     * is not valid anymore.
     * Ideally you should clean the current list in order to get up-to-date data.
     */
    abstract fun invalidatePages()
}
