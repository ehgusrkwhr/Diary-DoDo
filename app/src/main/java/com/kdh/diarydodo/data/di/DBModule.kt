package com.kdh.diarydodo.data.di

import android.content.Context
import androidx.room.Room
import com.kdh.diarydodo.data.db.DiaryDAO
import com.kdh.diarydodo.data.db.DiaryDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DBModule{

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
    ): DiaryDataBase = Room
        .databaseBuilder(context, DiaryDataBase::class.java, "memo.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDiaryDao(appDatabase: DiaryDataBase): DiaryDAO = appDatabase.diaryDAO()

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