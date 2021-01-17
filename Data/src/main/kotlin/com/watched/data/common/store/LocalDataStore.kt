package com.watched.data.common.store

interface LocalDataStore : DataStore {
    suspend fun isDataValid(): Boolean
    suspend fun clear()
}
