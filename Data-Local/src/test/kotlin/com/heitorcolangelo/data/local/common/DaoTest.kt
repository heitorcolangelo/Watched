package com.heitorcolangelo.data.local.common

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.heitorcolangelo.data.local.common.db.SkeletonDatabase
import org.junit.After
import org.junit.Rule

abstract class BaseDaoTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    protected val database: SkeletonDatabase = Room
        .inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            SkeletonDatabase::class.java
        )
        .allowMainThreadQueries()
        .build()

    @After
    fun tearDown() {
        database.close()
    }
}