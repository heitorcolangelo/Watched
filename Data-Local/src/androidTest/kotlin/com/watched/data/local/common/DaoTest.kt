package com.watched.data.local.common

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.watched.data.local.common.db.WatchedDatabase
import org.junit.After
import org.junit.Rule

abstract class DaoTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    protected val database = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        WatchedDatabase::class.java
    )
        .allowMainThreadQueries()
        .build()

    @After
    fun tearDown() {
        database.close()
    }
}
