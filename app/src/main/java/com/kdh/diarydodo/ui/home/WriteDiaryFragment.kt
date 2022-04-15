package com.kdh.diarydodo.ui.home

import PictureInterface
import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.kdh.diarydodo.databinding.FragmentWriteDiaryBinding
import com.kdh.diarydodo.ui.base.BaseFragment
import com.kdh.diarydodo.ui.custom.CustomDialog
import com.kdh.diarydodo.ui.viewmodel.DiaryViewModel
import com.kdh.diarydodo.util.DateUtil
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WriteDiaryFragment : BaseFragment<FragmentWriteDiaryBinding>() {


    private val viewModel: DiaryViewModel by viewModels()
    private val cameraPermissionCheckCallBack =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) pictureDialog()
            Log.d("dodo2 ", "무엇을처리")

        }
    private val pictureImageCheckCallBack = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        // Callback 작업 구현
        if (it.resultCode == RESULT_OK) {
            Log.d("dodo2 ", "성공")
            val bitmap = it.data?.extras?.get("data") as Bitmap
            binding.imageViewPicture.setImageBitmap(bitmap)

        } else {
            Log.d("dodo2 ", "실패")
        }
    }

    private val albumImageCheckCallBack = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        Log.d("dodo2 ", "it.resultCode ${it.resultCode}")
        // Callback 작업 구현
        if (it.resultCode == RESULT_OK) {
            it.data ?: return@registerForActivityResult
            val imageData = it.data!!.data as Uri
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val source = ImageDecoder.createSource(requireContext().contentResolver, imageData)
                val bitmap = ImageDecoder.decodeBitmap(source)
                binding.imageViewPicture.setImageBitmap(bitmap)
            } else {
                val bitmap = MediaStore.Images.Media.getBitmap(
                    requireContext().contentResolver,
                    imageData
                )
                binding.imageViewPicture.setImageBitmap(bitmap)
            }

        }
    }

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

        binding.imageViewPicture.setOnClickListener {
            checkPermission()
        }
    }

    private fun checkPermission() {
        var permission =
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
        if (permission == PackageManager.PERMISSION_GRANTED) {
            Log.d("dodo22", "카메라권한존재")
            pictureDialog()
        } else {
            Log.d("dodo22", "else")
            //val permissionLauncher = registerForActivityResult(ActivityResultContract.)
            cameraPermissionCheckCallBack.launch(Manifest.permission.CAMERA)
        }
        //ActivityCompat.requestPermissions(Activity(),arrayOf(Manifest.permission.CAMERA),REQ_CAMERA_PERMISSION)

    }

    private fun pictureDialog() {
        Log.d("dodo22 ", " pictureDialog")
        //val dialog = CustomDialog()
        val dialog = CustomDialog(requireContext(), object : PictureInterface {
            override fun onCameraStart() {
                Toast.makeText(activity, "카메라 클릭", Toast.LENGTH_SHORT).show()
                val pictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                pictureImageCheckCallBack.launch(pictureIntent)

            }

            override fun onAlbumStart() {
                Toast.makeText(activity, "앨범 클릭", Toast.LENGTH_SHORT).show()
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                albumImageCheckCallBack.launch(intent)
            }

        }).show()

    }


}



