package com.kdh.diarydodo.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query


@Dao
interface DiaryDAO {

    @Insert(onConflict = REPLACE)
    suspend fun insert(memo : DiaryEntity)

    @Query("SELECT * FROM memo")
    suspend fun getAll() : List<DiaryEntity>

    @Query("SELECT * FROM memo WHERE compareDate = :targetDate")
    suspend fun getSelectDateAll(targetDate: String) : List<DiaryEntity>

    @Delete()
    suspend fun delete(memo : DiaryEntity)
}