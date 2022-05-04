package com.kdh.diarydodo.data.db

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE


@Dao
interface DiaryDAO {

    @Insert(onConflict = REPLACE)
    suspend fun insert(memo : DiaryEntity)

    @Update
    suspend fun update(memo : DiaryEntity)

    @Query("SELECT * FROM memo")
    suspend fun getAll() : List<DiaryEntity>

    @Query("SELECT * FROM memo WHERE compareDate = :targetDate")
    suspend fun getSelectDateAll(targetDate: String) : List<DiaryEntity>

    @Query("DELETE FROM memo WHERE id = :id ")
    suspend fun delete(id : String)
}