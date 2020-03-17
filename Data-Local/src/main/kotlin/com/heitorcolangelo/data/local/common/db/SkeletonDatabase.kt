package com.heitorcolangelo.data.local.common.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.heitorcolangelo.data.local.common.constants.DbConstants
import com.heitorcolangelo.data.local.common.dao.ConfigDao
import com.heitorcolangelo.data.local.common.entity.ConfigEntity
import com.heitorcolangelo.data.local.dummy.dao.DummyDao
import com.heitorcolangelo.data.local.dummy.entity.DummyEntity

@Database(
    entities = [DummyEntity::class, ConfigEntity::class],
    version = DbConstants.VERSION,
    exportSchema = false
)
abstract class SkeletonDatabase : RoomDatabase() {

    abstract fun getDummyDao(): DummyDao

    abstract fun getConfigDao(): ConfigDao

    companion object {
        @Volatile
        private var INSTANCE: SkeletonDatabase? = null

        fun getDatabase(context: Context): SkeletonDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SkeletonDatabase::class.java,
                    DbConstants.DB_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}