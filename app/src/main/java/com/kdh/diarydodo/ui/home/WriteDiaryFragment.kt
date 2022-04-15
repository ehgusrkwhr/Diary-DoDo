package com.kdh.diarydodo.ui.home

import PictureInterface
import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.kdh.diarydodo.common.Constant.REQ_CAMERA_PERMISSION
import com.kdh.diarydodo.databinding.FragmentWriteDiaryBinding
import com.kdh.diarydodo.ui.base.BaseFragment
import com.kdh.diarydodo.ui.custom.CustomDialog
import com.kdh.diarydodo.ui.viewmodel.DiaryViewModel
import com.kdh.diarydodo.util.DateUtil
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WriteDiaryFragment : BaseFragment<FragmentWriteDiaryBinding>() {


    private val viewModel: DiaryViewModel by viewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWriteDiaryBinding {
        return FragmentWriteDiaryBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("dodo22 ", "WriteDiaryFragment onViewCreated")
        initView()
    }

    private fun initView() {
        binding.diaryReg.setOnClickListener {
            if (binding.editTextMemo.text.toString().isNotEmpty()) {
                Toast.makeText(activity, "등록!", Toast.LENGTH_SHORT).show()
                viewModel.insertDiaryInfo(
                    binding.editTextMemo.text.toString(),
                    DateUtil.getCurrentDateTime()
                )
            } else {
                Toast.makeText(activity, "내용을 입력하세요", Toast.LENGTH_SHORT).show()
            }
        }

        binding.textViewPictureDetail.setOnClickListener {
            Log.d("dodo2", "textViewPictureDetail")
            CustomDialog(requireContext(), object : PictureInterface {
                override fun onCameraStart() {
                    Toast.makeText(activity, "카메라 클릭", Toast.LENGTH_SHORT).show()


                }

                override fun onAlbumStart() {
                    Toast.makeText(activity, "앨범 클릭", Toast.LENGTH_SHORT).show()
                }

            }).show()
        }

    }

    private fun checkPermission() {
        var permission =
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
        if (permission == PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(Activity(),arrayOf(Manifest.permission.CAMERA),REQ_CAMERA_PERMISSION)
        }
    }


}



