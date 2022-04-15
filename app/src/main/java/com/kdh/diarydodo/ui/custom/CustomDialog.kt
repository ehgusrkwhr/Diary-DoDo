package com.kdh.diarydodo.ui.custom

import PictureInterface
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.kdh.diarydodo.databinding.CustomPictureDialogBinding

class CustomDialog(context: Context, pictureDialogInterface: PictureInterface) : Dialog(context),
    View.OnClickListener {
    private lateinit var binding: CustomPictureDialogBinding
    private var event = pictureDialogInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("dodo2 ", "CustomDialog 지닙")
        binding = CustomPictureDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
//        setContentView(R.layout.custom_picture_dialog)

    }

    private fun initView() {
        binding.imageViewCamera.setOnClickListener(this)
        binding.imageViewPicture.setOnClickListener(this)
        binding.imageViewCancel.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.imageViewCamera -> {
                event.onCameraStart()
                dismiss()
            }
            binding.imageViewPicture -> {
                event.onAlbumStart()
                dismiss()
            }
            binding.imageViewCancel -> dismiss()
        }
    }

}