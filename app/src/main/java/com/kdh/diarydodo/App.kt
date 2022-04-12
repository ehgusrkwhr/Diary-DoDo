package com.kdh.diarydodo

import android.app.Application
import com.kdh.diarydodo.common.ApplicationContextWrapper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    //lateinit var diaryRepository :
    override fun onCreate() {
        super.onCreate()
        ApplicationContextWrapper.instance = this
    }


}