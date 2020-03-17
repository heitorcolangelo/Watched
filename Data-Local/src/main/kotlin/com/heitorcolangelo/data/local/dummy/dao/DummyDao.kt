package com.heitorcolangelo.data.local.dummy.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heitorcolangelo.data.local.dummy.entity.DummyEntity
import io.reactivex.Flowable

@Dao
interface DummyDao {

    @Query("SELECT * FROM ${DummyEntity.TABLE_NAME}")
    fun getDummies(): Flowable<List<DummyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveDummies(dummyList: List<DummyEntity>)

    @Query("DELETE FROM ${DummyEntity.TABLE_NAME}")
    fun clearDummies()

}