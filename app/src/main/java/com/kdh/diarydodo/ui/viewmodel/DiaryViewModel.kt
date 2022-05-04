package com.kdh.diarydodo.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kdh.diarydodo.data.db.DiaryEntity
import com.kdh.diarydodo.data.repository.DiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val diaryRepository: DiaryRepository
) : ViewModel() {
//    val eventUserRepo: LiveData<List<DiaryEntity>> get() = _eventUserRepo
//    private val _eventUserRepo = MutableLiveData<List<DiaryEntity>>()

    val TAG = "dodo55"
    val eventUserRepo: LiveData<List<DiaryEntity>> get() = _eventUserRepo
    private val _eventUserRepo = MutableLiveData<List<DiaryEntity>>()

    val eventEqualDate: LiveData<List<DiaryEntity>> get() = _eventEqualDate
    private val _eventEqualDate = MutableLiveData<List<DiaryEntity>>()

    fun getDiaryAll() = viewModelScope.launch {
        val response = diaryRepository.getALLDiary()
        _eventUserRepo.postValue(response)
    }

    fun insertDiaryInfo(memo: String, date: String, id:String = "") = viewModelScope.launch {
        diaryRepository.insertDiary(memo, date)
        // _eventUserRepo.postValue(response)

    }

    fun getEqualDateDiary(date: String) = viewModelScope.launch {
        val response = diaryRepository.getEqualDateDiary(date)
        _eventEqualDate.postValue(response)
    }

    fun deleteDiary(id : String) = viewModelScope.launch {
        Log.d(TAG, "deleteDiary: $id ")
        diaryRepository.deleteDiary(id)
    }

    fun updateDiary(memo: String, date: String, id:Long = 0) = viewModelScope.launch {
        diaryRepository.updateDiary(memo, date,id)
        Log.d(TAG, "updateDiary: 업뎃 id ${id}")
        // _eventUserRepo.postValue(response)

    }

}