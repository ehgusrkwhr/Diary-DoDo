package com.kdh.diarydodo.ui.home

import IPicture
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
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.kdh.diarydodo.databinding.FragmentWriteDiaryBinding
import com.kdh.diarydodo.singleClick
import com.kdh.diarydodo.ui.base.BaseFragment
import com.kdh.diarydodo.ui.custom.CustomDialog
import com.kdh.diarydodo.ui.viewmodel.DiaryViewModel
import com.kdh.diarydodo.util.DataUtil
import com.kdh.diarydodo.util.DateUtil
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WriteDiaryFragment : BaseFragment<FragmentWriteDiaryBinding>() {


    private val viewModel: DiaryViewModel by viewModels()
    private val essentialPermissionsList = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result: MutableMap<String, Boolean> ->
            Log.d("dodo55 ", "result ${result}")
            val deniedList: List<String> = result.filter {
                !it.value
            }.map {
                it.key
            }
            Log.d("dodo55 ", "deniedList ${deniedList}")
            when {
                deniedList.isNotEmpty() -> {
                    val map = deniedList.groupBy { permission ->
                        if (shouldShowRequestPermissionRationale(permission)) "DENIED" else "EXPLAINED"
                    }
                    Log.d("dodo55 ", "map ${map}")
                    map["DENIED"]?.let {
                        //???????????? ?????????
                        Log.d("dodo55 ", "????????????")
                        Snackbar.make(
                            requireActivity().findViewById(android.R.id.content),
                            "?????? ?????????",
                            Snackbar.LENGTH_SHORT
                        )
                    }
                    map["EXPLAINED"]?.let {
                        //????????????
                        Log.d("dodo55 ", "????????????")
                        Snackbar.make(
                            requireActivity().findViewById(android.R.id.content),
                            "?????? ????????? ???????????? ?????????????????????.",
                            Snackbar.LENGTH_LONG
                        ).setAction("??????") {
                            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                .setData(Uri.parse("package:" + requireContext().packageName))
                            requireContext().startActivity(intent)
                        }.show()
                    }
                }
                else -> {
                    //?????? ?????? ??????
                    Log.d("dodo55 ", "?????? ?????? ??????")
                    pictureDialog()
                }
            }


        }


    private val cameraPermissionCheckCallBack =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) pictureDialog()
            Log.d("dodo2 ", "???????????????")

        }
    private val pictureImageCheckCallBack = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        // Callback ?????? ??????
        if (it.resultCode == RESULT_OK) {
            Log.d("dodo2 ", "??????")
            val bitmap = it.data?.extras?.get("data") as Bitmap
            binding.imageViewPicture.setImageBitmap(bitmap)

        } else {
            Log.d("dodo2 ", "??????")
        }
    }

    private val albumImageCheckCallBack = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        Log.d("dodo2 ", "it.resultCode ${it.resultCode}")
        // Callback ?????? ??????
        if (it.resultCode == RESULT_OK) {
            it.data ?: return@registerForActivityResult
            val imageData = it.data!!.data as Uri
            Log.d("dodo55 ", ": imageData ${imageData}")
//            val takeFlags =
//                Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
//            requireContext().contentResolver.takePersistableUriPermission(imageData, takeFlags)
     //       Log.d("dodo55 ", ": DataUtil.getPath(imageData) ${DataUtil.getPath(imageData)}")
            Log.d("dodo55 ", ": uri2path ${DataUtil.uri2path(imageData)}")

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val source = ImageDecoder.createSource(requireContext().contentResolver, imageData)
                val bitmap = ImageDecoder.decodeBitmap(source)
                Log.d("dodo55 ", ": source ${source}")
                Log.d("dodo55 ", ": bitmap ${bitmap}")
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
        Log.d("dodo55 ", "onViewCreated write")
        Log.d("dodo22 ", "WriteDiaryFragment onViewCreated")
        initView()
    }

    private fun initView() {
        binding.diaryReg.setOnClickListener {
            if (binding.editTextMemo.text.toString().isNotEmpty()) {
                Toast.makeText(activity, "??????!", Toast.LENGTH_SHORT).show()
                //????????? ?????? ??????

                if (arguments?.getString("id").isNullOrEmpty()) {
                    Log.d("dodo55", "initView: ??????  ")
                    viewModel.insertDiaryInfo(
                        binding.editTextMemo.text.toString(),
                        DateUtil.getCurrentDateTime()
                    )
                } else {
                    Log.d("dodo55", "initView: ??????  ")
                    viewModel.updateDiary(
                        binding.editTextMemo.text.toString(),
                        DateUtil.getCurrentDateTime(),
                        arguments?.getString("id")!!.toLong()
                        //id
                    )
                }

            } else {
                Toast.makeText(activity, "????????? ???????????????", Toast.LENGTH_SHORT).show()
            }
        }

        binding.imageViewPicture.singleClick {
            requestPermissionLauncher.launch(essentialPermissionsList)
            //  checkPermission()
        }
    }

    private fun checkPermission() {
        var permission =
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
        if (permission == PackageManager.PERMISSION_GRANTED) {
            Log.d("dodo22", "?????????????????????")
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
        val dialog = CustomDialog(requireContext(), object : IPicture {
            override fun onCameraStart() {
                Toast.makeText(activity, "????????? ??????", Toast.LENGTH_SHORT).show()
                val pictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                pictureImageCheckCallBack.launch(pictureIntent)

            }

            override fun onAlbumStart() {
                Toast.makeText(activity, "?????? ??????", Toast.LENGTH_SHORT).show()
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                albumImageCheckCallBack.launch(intent)
            }

        }).show()

    }

//    private fun permissionSnackBar(){
//        // ???????????? ????????? ??????????????? ?????? ???????????? ????????? ????????? ??????
//    // requestPermission??? ???????????? ?????? ???????????? ?????? ????????? ??????????????? ????????????.
//    val snackBar = Snackbar.make(layout, "R.string.suggest_permissison_grant_in_setting", Snackbar.LENGTH_INDEFINITE)
//        snackBar.setAction("??????") {
//            val intent = Intent()
//            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
//            val uri = Uri.fromParts("package", packageName, null)
//            intent.data = uri
//            startActivity(intent) } snackBar.show()
//
//
//    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("dodo55 ", "onDestroy write")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("dodo55 ", "onDetach write")
    }
}



