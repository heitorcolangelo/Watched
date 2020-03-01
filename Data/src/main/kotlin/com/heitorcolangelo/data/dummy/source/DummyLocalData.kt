package com.heitorcolangelo.data.dummy.source

import com.heitorcolangelo.data.common.source.LocalData
import com.heitorcolangelo.data.dummy.entity.DummyEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface DummyLocalData: LocalData {
    fun saveDummies(dummies: List<DummyEntity>): Completable

    fun getDummies(): Observable<List<DummyEntity>>
}
