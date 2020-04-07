package com.heitorcolangelo.domain.dummy.repository

import com.heitorcolangelo.domain.dummy.model.DummiesDomainModel
import io.reactivex.rxjava3.core.Observable

interface DummyRepository {
    fun getDummies(): Observable<DummiesDomainModel>
}
