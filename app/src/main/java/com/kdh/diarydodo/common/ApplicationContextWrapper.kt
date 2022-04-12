package com.kdh.diarydodo.common

import android.content.Context
import androidx.core.content.ContextCompat

class ApplicationContextWrapper {
    companion object {
        lateinit var instance: Context

        fun getString(id: Int): String = instance.getString(id)
        fun getColor(id: Int): Int = ContextCompat.getColor(instance,id)

    }
}