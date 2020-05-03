package com.heitorcolangelo.data.dummy.source

import com.heitorcolangelo.data.common.source.LocalData
import com.heitorcolangelo.data.dummy.model.DummyDataModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface DummyLocalData : LocalData {
    override val dataConfigId: String
        get() = DummyLocalData::class.java.name

    fun saveDummies(dummies: List<DummyDataModel>): Completable

    fun getDummies(): Observable<List<DummyDataModel>>
}
