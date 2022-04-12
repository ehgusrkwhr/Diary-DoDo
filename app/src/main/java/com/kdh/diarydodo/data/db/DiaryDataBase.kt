package com.kdh.diarydodo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [DiaryEntity::class], version = 1)
abstract class DiaryDataBase : RoomDatabase() {
    abstract fun diaryDAO(): DiaryDAO

//    companion object {
//        @Volatile var INSTANCE: DiaryDataBase? = null
//        fun getInstance(context: Context): DiaryDataBase? {
//            if (INSTANCE == null) {
//                synchronized(DiaryDataBase::class) {
//                    INSTANCE = Room.databaseBuilder(
//                        context.applicationContext,
//                        DiaryDataBase::class.java, "memo.db"
//                    )
//                        .fallbackToDestructiveMigration()
//                        .build()
//                }
//            }
//            return INSTANCE
//        }
//    }
}