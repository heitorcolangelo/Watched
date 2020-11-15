package com.heitorcolangelo.common.test

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

    fun randomId() = randomString()

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    fun randomFloat(): Float {
        return Math.random().toFloat()
    }

    fun randomInt(): Int {
        return Math.random().toInt()
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }

    fun randomLong(): Long {
        return Math.random().toLong()
    }
}
