package com.kdh.diarydodo.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@Entity(tableName = "memo")
data class DiaryEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    var memo: String = "",
    var date: String = "",
    var compareDate: String =""
)