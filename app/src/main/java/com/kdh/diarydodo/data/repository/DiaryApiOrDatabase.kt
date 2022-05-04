package com.kdh.diarydodo.data.repository

import com.kdh.diarydodo.data.db.DiaryEntity

interface DiaryApiOrDatabase {

    suspend fun getALLDiary(): List<DiaryEntity>
    suspend fun insertDiary(memo: String, date: String)
    suspend fun deleteDiary(id: String)
    suspend fun getEqualDateDiary(date: String): List<DiaryEntity>
    suspend fun updateDiary(memo: String, date: String, id: Long)
}