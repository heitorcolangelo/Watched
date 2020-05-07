package com.heitorcolangelo.data.local.factory

import java.util.UUID
import java.util.concurrent.ThreadLocalRandom

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

    fun randomString() = randomId()

    fun randomFloat(): Float {
        return Math.random().toFloat()
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }

    fun randomLong(): Long {
        return randomInt().toLong()
    }

    fun randomInt(): Int {
        return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
    }
}
