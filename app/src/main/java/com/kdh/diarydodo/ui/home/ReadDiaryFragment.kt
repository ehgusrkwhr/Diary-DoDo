package com.kdh.diarydodo.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kdh.diarydodo.adapter.DiaryListAdapter
import com.kdh.diarydodo.databinding.FragmentReadDiaryBinding
import com.kdh.diarydodo.ui.base.BaseFragment
import com.kdh.diarydodo.ui.custom.RemoveAndUpdateDialog
import com.kdh.diarydodo.ui.listener.IDialog
import com.kdh.diarydodo.ui.viewmodel.DiaryViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ReadDiaryFragment : BaseFragment<FragmentReadDiaryBinding>() {

    private val viewModel: DiaryViewModel by viewModels()

    //    private val listAdapter: DiaryListAdapter by lazy {
//        DiaryListAdapter(requireContext())
//    }
    private var listAdapter: DiaryListAdapter? = null

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentReadDiaryBinding {
        return FragmentReadDiaryBinding.inflate(inflater, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("dodo55 ", "onViewCreated read")
        initDiaryView()
        observeViewModel()
        initView()

    }

    private fun initView() {
        listAdapter = DiaryListAdapter().apply {
            setDialog(object : IDialog {
                override fun openDialog() {
                    RemoveAndUpdateDialog(requireContext()).show()
                }
            })
        }
        binding.readDiaryListView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = listAdapter
//            val itemTouchHelperCallback = ItemTouchHelperCallback(this, requireContext()).apply {
//                setClamp(resources.displayMetrics.widthPixels.toFloat() / 4)
//            }
//            ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(this)

        }
    }


    private fun initDiaryView() {
        viewModel.getDiaryAll()
    }

    private fun observeViewModel() {
        viewModel.eventUserRepo.observe(viewLifecycleOwner) {
            listAdapter?.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        listAdapter = null
        Log.d("dodo55 ", "onDestroyView read")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("dodo55 ", "onDestroy read")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("dodo55 ", "onDetach read")
    }
}