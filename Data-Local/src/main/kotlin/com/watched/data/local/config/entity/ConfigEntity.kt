package com.watched.data.local.config.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.watched.data.local.common.entity.BaseEntity

@Entity(tableName = ConfigEntity.TABLE_NAME)
data class ConfigEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = COLUMN_LAST_CACHE_TIME)
    val lastCacheTime: Long
) : BaseEntity {
    companion object {
        const val TABLE_NAME = "config"
        const val COLUMN_LAST_CACHE_TIME = "last_cache_time"

        fun getDefault() = ConfigEntity(BaseEntity.NO_ID, 0L)
    }
}
