package com.heitorcolangelo.data.common

import com.heitorcolangelo.data.common.source.LocalData
import com.heitorcolangelo.data.common.store.DataStore
import com.heitorcolangelo.data.common.store.DataStoreProvider
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

abstract class RepositoryImpl<DS : DataStore, DSP : DataStoreProvider<DS>>(
    private val dataStoreProvider: DSP,
    private val localData: LocalData
) {
    fun getDataStore(): Observable<DS> {
        return Observable.zip(
            localData.isDataCached().toObservable(),
            localData.isCacheExpired().toObservable(),
            BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { isDataCached, isCacheExpired ->
                Pair(
                    isDataCached,
                    isCacheExpired
                )
            }
        ).map {
            dataStoreProvider.getDataStore(it.first, it.second)
        }
    }
}