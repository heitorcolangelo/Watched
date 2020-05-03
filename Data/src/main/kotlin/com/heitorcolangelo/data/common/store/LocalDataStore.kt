package com.heitorcolangelo.data.common.store

import io.reactivex.rxjava3.core.Observable

interface LocalDataStore : DataStore {
    fun isDataValid(): Observable<Boolean>
}
