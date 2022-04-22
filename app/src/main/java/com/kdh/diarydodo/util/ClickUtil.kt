package com.kdh.diarydodo.util

import android.view.View

class setSingleClickUtil(private val onSingleClick: (View) -> Unit) : View.OnClickListener {

    companion object {
        const val CLICK_INTERVAL = 300
    }

    private var lastClickedTime: Long = 0L

    private fun isSafe(): Boolean {
        return System.currentTimeMillis() - lastClickedTime > CLICK_INTERVAL
    }

    override fun onClick(v: View?) {
        if (isSafe() && v != null) {
            onSingleClick(v)
        }
        lastClickedTime = System.currentTimeMillis()
    }

}
