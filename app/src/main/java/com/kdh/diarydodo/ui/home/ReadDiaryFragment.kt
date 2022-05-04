package com.kdh.diarydodo.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kdh.diarydodo.R
import com.kdh.diarydodo.adapter.DiaryListAdapter
import com.kdh.diarydodo.databinding.FragmentReadDiaryBinding
import com.kdh.diarydodo.ui.base.BaseFragment
import com.kdh.diarydodo.ui.custom.IDialogUpdateDiary
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
                override fun openDialog(id: String) {
                    RemoveAndUpdateDialog(requireContext(), object : IDialogUpdateDiary {
                        override fun leftButton() {
                            RemoveAndUpdateDialog(requireContext(), object : IDialogUpdateDiary {
                                override fun leftButton() {
                                    Toast.makeText(requireContext(), "삭제", Toast.LENGTH_SHORT)
                                        .show()
                                    viewModel.deleteDiary(id)
                                    initDiaryView()
                                }

                                override fun rightButton() {
                                    Toast.makeText(requireContext(), "취소", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }).apply {
                                setDialogTitle("정말 삭제하시겠습니까?")
                                setLeftButtonTitle("확인")
                                setRightButtonTitle("취소")
                            }.show()
                        }

                        override fun rightButton() {
                            Toast.makeText(requireContext(), "업뎃", Toast.LENGTH_SHORT).show()
                            val bundle = bundleOf("id" to id)
                            // view?.findNavController()?.navigate(R.id.writeDiaryFragment, bundle)
                            //NavHostFragment.findNavController(this@ReadDiaryFragment).findDestination(R.id.writeDiaryFragment)
                            NavHostFragment.findNavController(this@ReadDiaryFragment)
                                .navigate(R.id.writeDiaryFragment, bundle)

                        }
                    }).show()

                }
            })
        }
        binding.readDiaryListView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = listAdapter
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