package com.kdh.diarydodo.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.kdh.diarydodo.MainActivity
import com.kdh.diarydodo.common.ApplicationContextWrapper
import com.kdh.diarydodo.data.db.DiaryDataBase
import com.kdh.diarydodo.data.db.DiaryEntity
import com.kdh.diarydodo.databinding.FragmentWriteDiaryBinding
import com.kdh.diarydodo.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WriteDiaryFragment : BaseFragment<FragmentWriteDiaryBinding>() {
    // private var mBinding: FragmentWriteDiaryBinding? = null
    private lateinit var db: DiaryDataBase
    var diaryList = listOf<DiaryEntity>()


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWriteDiaryBinding {
        return FragmentWriteDiaryBinding.inflate(inflater, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("dodo22 ", "WriteDiaryFragment onViewCreated")
//        if(mainActivity == null){
//            Log.d("dodo22 ", "null")
//        }else{
//            Log.d("dodo22 ", "not null")
//        }

       // db = DiaryDataBase.getInstance(mainActivity)!!
        //val binding = FragmentWriteDiaryBinding.inflate(layoutInflater)
        binding.diaryReg.setOnClickListener {
            Log.d("dodo22 ", "클릭1111")
          //  insertDiary()
            Toast.makeText(activity, "클릭!", Toast.LENGTH_SHORT).show()

        }
    }

//    private fun insertDiary() {
//
//        CoroutineScope(Dispatchers.IO).launch {
//
//        }
//
//        lifecycleScope.launch(Dispatchers.IO) {
//            val memo = DiaryEntity(null, binding.editTextMemo.toString())
//            db.diaryDAO().insert(memo)
//            diaryList = db.diaryDAO().getAll()
//            Log.d("dodo22 ", diaryList.toString())
//            for(i in diaryList){
//                Log.d("dodo22 ", "몇번 ${i}")
//            }
//        }
//    }
}



