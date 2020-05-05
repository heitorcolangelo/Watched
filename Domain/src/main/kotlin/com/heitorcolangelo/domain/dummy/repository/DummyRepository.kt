package com.heitorcolangelo.domain.dummy.repository

import com.heitorcolangelo.domain.common.model.PageDomainModel
import com.heitorcolangelo.domain.dummy.model.DummyDomainModel
import io.reactivex.rxjava3.core.Observable

interface DummyRepository {
    fun getDummies(): Observable<PageDomainModel<DummyDomainModel>>
}
