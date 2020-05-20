package com.heitorcolangelo.data.common.store

interface DataStore {
    /**
     * This can be later on moved into some sort of remote config implementation.
     */
    companion object {
        const val PAGE_SIZE = 20
    }
}
