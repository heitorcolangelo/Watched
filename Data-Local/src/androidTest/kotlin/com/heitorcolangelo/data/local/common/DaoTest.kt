package com.heitorcolangelo.data.local.common

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.heitorcolangelo.data.local.common.db.SkeletonDatabase
import org.junit.After
import org.junit.Rule

abstract class DaoTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    protected val database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            SkeletonDatabase::class.java
        )
        .allowMainThreadQueries()
        .build()

    @After
    fun closeDb() {
        database.close()
    }
}
