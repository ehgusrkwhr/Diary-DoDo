package com.kdh.diarydodo.data.repository

import com.kdh.diarydodo.data.db.DiaryDAO
import com.kdh.diarydodo.data.db.DiaryEntity
import javax.inject.Inject

class DiaryRepository @Inject constructor(private val diaryDAO : DiaryDAO) {

    suspend fun getALLDiary() : List<DiaryEntity>{
        return diaryDAO.getAll()
    }

    suspend fun insertDiary(memo : String){
        diaryDAO.insert(DiaryEntity(null,memo))
    }

    suspend fun deleteDiary(info : DiaryEntity){
        diaryDAO.delete(info)
    }
}