package com.kdh.diarydodo.ui.custom

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.kdh.diarydodo.databinding.CustomPictureDialogBinding
import com.kdh.diarydodo.databinding.DialogUpdateDiaryBinding

class RemoveAndUpdateDialog(context: Context) : Dialog(context){
    private lateinit var binding : DialogUpdateDiaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogUpdateDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}