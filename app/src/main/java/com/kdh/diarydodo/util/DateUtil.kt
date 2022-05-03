package com.kdh.diarydodo.util

import android.os.Build
import android.util.Log
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateUtil {

    fun getCurrentDateTime(): String {
        var dateValue = ""
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
//            val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm")
            dateValue = current.format(formatter)
        } else {
            val currentTime = System.currentTimeMillis()
            val formatter = SimpleDateFormat("yyyy-MM-dd-hh-mm")
            dateValue = formatter.format(currentTime)
        }
        return dateValue

    }

    fun equalDate(date: String): String {
        val temp = date.split("-")
        return temp[0] + temp[1] + temp[2]
    }

    fun calendarDateEqual(date: String): String {
        val temp = date.substring(date.indexOf("{") + 1, date.indexOf("}"))
        val tm = temp.split("-")
        var box = ""
        for (index in tm.indices) {
            box += if (index >= 1 && 1 >= tm[index].length) {
                if (index == 1) "0" + (tm[index].toInt() + 1)
                else "0" + tm[index]
            } else {
                tm[index]
            }
        }
        return box
    }

}