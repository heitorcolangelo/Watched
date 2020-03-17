package com.heitorcolangelo.data.dummy.source

import com.heitorcolangelo.data.common.source.LocalData
import com.heitorcolangelo.data.dummy.model.DummyDataModel
import io.reactivex.Completable
import io.reactivex.Observable

interface DummyLocalData : LocalData {
    fun saveDummies(dummies: List<DummyDataModel>): Completable

    fun getDummies(): Observable<List<DummyDataModel>>
}
