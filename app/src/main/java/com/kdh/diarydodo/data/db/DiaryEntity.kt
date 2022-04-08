package com.kdh.diarydodo.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "memo")
class DiaryEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    var memo: String = ""
)