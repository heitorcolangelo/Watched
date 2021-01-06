package com.heitorcolangelo.data.local.config.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heitorcolangelo.data.local.config.entity.ConfigEntity

@Dao
interface ConfigDao {

    @Query("SELECT * FROM ${ConfigEntity.TABLE_NAME} WHERE id = :dataConfigId")
    suspend fun getConfig(dataConfigId: String): List<ConfigEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveConfig(config: ConfigEntity)

    @Query("DELETE FROM ${ConfigEntity.TABLE_NAME} WHERE id = :dataConfigId")
    suspend fun deleteConfig(dataConfigId: String)
}
