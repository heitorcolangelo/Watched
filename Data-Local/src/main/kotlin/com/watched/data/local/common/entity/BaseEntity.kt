package com.watched.data.local.common.entity

import androidx.room.Ignore

internal const val NO_ID = "NO_ID"

abstract class BaseEntity(@Ignore open val id: String)
