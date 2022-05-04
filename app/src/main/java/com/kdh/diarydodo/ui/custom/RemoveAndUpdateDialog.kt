package com.kdh.diarydodo.ui.custom

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import com.kdh.diarydodo.databinding.DialogUpdateDiaryBinding

class RemoveAndUpdateDialog(context: Context, iDialogUpdateDiary: IDialogUpdateDiary) :
    Dialog(context), View.OnClickListener {
    private lateinit var binding: DialogUpdateDiaryBinding
    private var title: String? = null
    private var lTitle: String? = null
    private var rTitle: String? = null
    private var iDialogUpdateDiary: IDialogUpdateDiary = iDialogUpdateDiary
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogUpdateDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.tvDialogRemove.setOnClickListener(this)
        binding.tvDialogUpdate.setOnClickListener(this)

        title?.let { binding.tvDialogTitle.text = it }
        lTitle?.let { binding.tvDialogRemove.text = it }
        rTitle?.let { binding.tvDialogUpdate.text = it }
    }

    fun setDialogTitle(text: String) {
        title = text
    }

    fun setLeftButtonTitle(text: String) {
        lTitle = text
    }

    fun setRightButtonTitle(text: String) {
        rTitle = text
    }

//    fun setIUpdateDiary(IDialog : IDialogUpdateDiary){
//        iDialogUpdateDiary= IDialog
//    }

    override fun onClick(v: View?) {
        when (v) {
            binding.tvDialogRemove -> {
                iDialogUpdateDiary.leftButton()
                dismiss()
            }
            binding.tvDialogUpdate -> {
                iDialogUpdateDiary.rightButton()
                dismiss()
            }
        }
    }
}

interface IDialogUpdateDiary {
    fun leftButton()
    fun rightButton()
}
