package com.heitorcolangelo.domain.dummy.repository

import com.heitorcolangelo.domain.dummy.model.DummiesDomainModel
import io.reactivex.Observable

interface DummyRepository {
    fun getDummies(): Observable<DummiesDomainModel>
}
