package com.kdh.diarydodo.data.repository
import android.util.Log
import com.kdh.diarydodo.data.db.DiaryDAO
import com.kdh.diarydodo.data.db.DiaryEntity
import com.kdh.diarydodo.util.DateUtil.equalDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class DiaryRepository @Inject constructor(private val diaryDAO: DiaryDAO) : DiaryApiOrDatabase {


    override suspend fun getALLDiary(): List<DiaryEntity> {
        return withContext(Dispatchers.IO) {
            diaryDAO.getAll()
        }
    }

    override suspend fun insertDiary(memo: String, date: String) {
        //비교시간

        diaryDAO.insert(DiaryEntity(null, memo, date, equalDate(date)))
    }

    override suspend fun deleteDiary(id: String) {
        diaryDAO.delete(id)
    }

    override suspend fun getEqualDateDiary(date: String) : List<DiaryEntity> {
        Log.d("dodo22 ","getEqualDateDiary date ${date}")
        return withContext(Dispatchers.IO) {
            diaryDAO.getSelectDateAll(date)
        }
    }

    override suspend fun updateDiary(memo: String, date: String, id: Long) {
        diaryDAO.update(DiaryEntity(id, memo, date, equalDate(date)))
    }

}