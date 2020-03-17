package com.heitorcolangelo.data.local.dummy.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.heitorcolangelo.data.local.common.entity.BaseEntity

@Entity(tableName = DummyEntity.TABLE_NAME)
data class DummyEntity(
    @PrimaryKey override val id: String
) : BaseEntity(id) {
    companion object {
        const val TABLE_NAME = "dummy"
    }
}
