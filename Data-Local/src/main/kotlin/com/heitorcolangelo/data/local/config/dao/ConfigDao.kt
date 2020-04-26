package com.heitorcolangelo.data.local.config.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heitorcolangelo.data.local.config.entity.ConfigEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface ConfigDao {

    @Query("SELECT * FROM ${ConfigEntity.TABLE_NAME} WHERE id = :dataConfigId")
    fun getConfig(dataConfigId: String): Flowable<List<ConfigEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveConfig(config: ConfigEntity): Completable

    @Query("DELETE FROM ${ConfigEntity.TABLE_NAME} WHERE id = :dataConfigId")
    fun deleteConfig(dataConfigId: String): Completable
}
