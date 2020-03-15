package com.heitorcolangelo.data.common.store

import io.reactivex.Observable

interface LocalDataStore: DataStore {
    fun isDataValid(): Observable<Boolean>
}