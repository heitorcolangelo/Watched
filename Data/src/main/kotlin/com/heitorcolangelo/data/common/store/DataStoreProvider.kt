package com.heitorcolangelo.data.common.store

abstract class DataStoreProvider<DS : DataStore>(
    private val localDataStore: DS,
    private val remoteDataStore: DS
) {
    open fun getDataStore(isDataCached: Boolean, isCacheExpired: Boolean): DS {
        return if (isDataCached && !isCacheExpired) {
            localDataStore
        } else {
            remoteDataStore
        }
    }

    open fun getLocalDataStore() = localDataStore
}
