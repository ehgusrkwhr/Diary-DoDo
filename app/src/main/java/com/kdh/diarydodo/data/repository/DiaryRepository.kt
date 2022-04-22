package com.kdh.diarydodo.data.repository

import com.kdh.diarydodo.data.db.DiaryDAO
import com.kdh.diarydodo.data.db.DiaryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


class DiaryRepository @Inject constructor(private val diaryDAO: DiaryDAO) {

    suspend fun getALLDiary(): List<DiaryEntity> {
        return withContext(Dispatchers.IO) {
            diaryDAO.getAll()
        }
    }
    // return diaryDAO.getAll()


    suspend fun insertDiary(memo: String,date : String) {
        diaryDAO.insert(DiaryEntity(null, memo,date))
    }

    suspend fun deleteDiary(info: DiaryEntity) {
        diaryDAO.delete(info)
    }
}