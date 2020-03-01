package com.heitorcolangelo.data.dummy.store

import com.heitorcolangelo.data.common.store.DataStoreProvider
import javax.inject.Inject

class DummyDataStoreProvider @Inject constructor(
    localDataStore: DummyLocalDataStore,
    remoteDataStore: DummyRemoteDataStore
) : DataStoreProvider<DummyDataStore>(localDataStore, remoteDataStore)