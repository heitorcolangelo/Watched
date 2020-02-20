package com.heitorcolangelo.domain.dummy.repository

import com.heitorcolangelo.domain.dummy.model.DummyDomainModel
import io.reactivex.Observable

interface DummyRepository {
    fun getDummies(): Observable<List<DummyDomainModel>>
}