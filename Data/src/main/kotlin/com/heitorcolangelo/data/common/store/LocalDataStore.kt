package com.heitorcolangelo.data.common.store

interface LocalDataStore : DataStore {
    suspend fun isDataValid(): Boolean
    suspend fun clear()
}
