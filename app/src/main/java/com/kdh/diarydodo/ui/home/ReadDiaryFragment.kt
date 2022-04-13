package com.kdh.diarydodo.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.kdh.diarydodo.adapter.DiaryListAdapter
import com.kdh.diarydodo.data.db.DiaryDataBase
import com.kdh.diarydodo.databinding.FragmentReadDiaryBinding
import com.kdh.diarydodo.helper.ItemTouchHelperCallback
import com.kdh.diarydodo.ui.base.BaseFragment
import com.kdh.diarydodo.ui.viewmodel.DiaryViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ReadDiaryFragment : BaseFragment<FragmentReadDiaryBinding>() {

    private lateinit var db: DiaryDataBase

    private val viewModel: DiaryViewModel by viewModels()

    //    private lateinit var listAdapter: DiaryListAdapter
    private val listAdapter: DiaryListAdapter by lazy {
        Log.d("dodo2", "listAdapter")
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
        binding.readDiaryListView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = listAdapter
            val itemTouchHelperCallback = ItemTouchHelper(ItemTouchHelperCallback(this))
            itemTouchHelperCallback.attachToRecyclerView(this)
        }
        initDiaryView()
        observeViewModel()
    }

    private fun initDiaryView() {
        Log.d("dodo2", "initDiaryView444")
        viewModel.getDiaryAll()
    }

    private fun observeViewModel() {
        Log.d("dodo2", "observeViewModel111")
        viewModel.eventUserRepo.observe(viewLifecycleOwner) {
            Log.d("dodo2", "observeViewModel222")
            if (!it.isNullOrEmpty()) {
                Log.d("dodo2", "데이터존재 ${it.toString()}")
            }
            listAdapter.submitList(it)

            //listAdapter.notifyDataSetChanged()
        }
    }


//    private fun insertDiary() {
//        lifecycleScope.launch(Dispatchers.IO) {
//            val memo = DiaryEntity(null, binding.editTextMemo.toString())
//            db.diaryDAO().insert(memo)
//        }
//    }

}