package com.kdh.diarydodo.data.repository

import com.kdh.diarydodo.data.db.DiaryDAO
import com.kdh.diarydodo.data.db.DiaryEntity
import com.kdh.diarydodo.ui.base.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DiaryRepository @Inject constructor(private val diaryDAO : DiaryDAO) {

     fun getALLDiary() : Flow<UiState> = flow<UiState>{

     }
       // return diaryDAO.getAll()


     fun insertDiary(memo : String){
        diaryDAO.insert(DiaryEntity(null,memo))
    }

     fun deleteDiary(info : DiaryEntity){
        diaryDAO.delete(info)
    }
}