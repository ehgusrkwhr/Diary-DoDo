package com.kdh.diarydodo.util

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateUtil {

    fun getCurrentDateTime() : String{
        var dateValue = ""
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")
            dateValue = current.format(formatter)
        }else{
            val currentTime = System.currentTimeMillis()
            val formatter = SimpleDateFormat("yyyy-MM-dd-hh-mm")
            dateValue = formatter.format(currentTime)
        }
        return dateValue

    }

}