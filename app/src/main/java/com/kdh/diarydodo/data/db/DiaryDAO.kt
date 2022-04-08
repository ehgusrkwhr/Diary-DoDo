package com.kdh.diarydodo.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query


@Dao
interface DiaryDAO {

    @Insert(onConflict = REPLACE)
    fun insert(memo : DiaryEntity)

    @Query("SELECT * FROM memo")
    fun getAll() : List<DiaryEntity>

    @Delete()
    fun delete(memo : DiaryEntity)
}