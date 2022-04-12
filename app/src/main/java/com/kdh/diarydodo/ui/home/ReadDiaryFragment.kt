package com.kdh.diarydodo.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kdh.diarydodo.adapter.DiaryListAdapter
import com.kdh.diarydodo.data.DiaryInfo
import com.kdh.diarydodo.data.db.DiaryDataBase
import com.kdh.diarydodo.data.db.DiaryEntity
import com.kdh.diarydodo.databinding.FragmentReadDiaryBinding
import com.kdh.diarydodo.ui.base.BaseFragment
import com.kdh.diarydodo.ui.viewmodel.DiaryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ReadDiaryFragment : BaseFragment<FragmentReadDiaryBinding>() {

    private lateinit var db: DiaryDataBase
    private lateinit var list: ArrayList<DiaryInfo>
    private val viewModel : DiaryViewModel by viewModels()
    //    private lateinit var listAdapter: DiaryListAdapter
    private val listAdapter: DiaryListAdapter by lazy {
        DiaryListAdapter()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentReadDiaryBinding {
        return FragmentReadDiaryBinding.inflate(inflater, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentReadDiaryBinding.inflate(layoutInflater)
        list = ArrayList()
        for (i in 1..10) {
            val temp = DiaryInfo()
            list.add(temp)
        }


        binding.readDiaryListView.apply {
            Log.d("dodo2", "layoutManager")
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = listAdapter
        }
        listAdapter.submitList(list)
        Log.d("dodo2 ", list.toString())


    }

//    private fun insertDiary() {
//        lifecycleScope.launch(Dispatchers.IO) {
//            val memo = DiaryEntity(null, binding.editTextMemo.toString())
//            db.diaryDAO().insert(memo)
//        }
//    }

}