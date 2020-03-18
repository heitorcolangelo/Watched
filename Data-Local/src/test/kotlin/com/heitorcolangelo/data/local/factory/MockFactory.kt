package com.heitorcolangelo.data.local.factory

import java.util.UUID

interface MockFactory<T> {
    fun make(): T

    fun makeList(elements: Int): List<T> {
        return mutableListOf<T>().also { list ->
            repeat(elements) {
                list.add(make())
            }
        }
    }

    fun randomId(): String {
        return UUID.randomUUID().toString()
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }
}
