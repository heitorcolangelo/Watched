package com.heitorcolangelo.domain.factory

import java.util.*

interface MockFactory<T> {
    fun make(): T

    fun makeList(elements: Int): List<T> {
        return mutableListOf<T>().also { list ->
            repeat(elements) {
                list.add(make())
            }
        }
    }

    fun randomUuid(): String {
        return UUID.randomUUID().toString()
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }
}