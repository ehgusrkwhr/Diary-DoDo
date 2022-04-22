package com.kdh.diarydodo

import android.view.View
import com.kdh.diarydodo.util.setSingleClickUtil

fun View.singleClick(onSingleClick : (View) -> Unit){
    val oneClick = setSingleClickUtil{
        onSingleClick(it)
    }
    setOnClickListener(oneClick)
}