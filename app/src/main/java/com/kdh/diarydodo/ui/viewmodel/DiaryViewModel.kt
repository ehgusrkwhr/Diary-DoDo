package com.kdh.diarydodo.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.kdh.diarydodo.data.repository.DiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val diaryRepository: DiaryRepository
) : ViewModel() {

}