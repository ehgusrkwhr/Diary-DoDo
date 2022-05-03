package com.kdh.diarydodo.helper

import com.google.firebase.crashlytics.FirebaseCrashlytics

object CrashlyticsHelper {

    fun setEnabled(enabled : Boolean){
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(enabled)
    }

//    fun log(throwable : Throwable){
//        if(throwable !is HttpException){
//            return
//        }
//        log(throwable.response().toString)
//    }

    fun log(message : String){
        val throwable = Throwable(message)
        FirebaseCrashlytics.getInstance().recordException(throwable)
    }

}