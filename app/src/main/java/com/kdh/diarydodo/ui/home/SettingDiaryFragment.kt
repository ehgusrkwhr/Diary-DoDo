package com.kdh.diarydodo.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.kdh.diarydodo.databinding.FragmentSettingDiaryBinding
import com.kdh.diarydodo.ui.base.BaseFragment
import com.kdh.diarydodo.ui.viewmodel.DiaryViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingDiaryFragment : BaseFragment<FragmentSettingDiaryBinding>() {
    private val TAG = "dodo66"

    private val viewModel: DiaryViewModel by viewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSettingDiaryBinding {
        return FragmentSettingDiaryBinding.inflate(inflater, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("dodo55 ", "onViewCreated write")
        Log.d("dodo22 ", "SettingDiaryFragment onViewCreated")
        initView()
    }

    private fun initView() {
        var locationArray = IntArray(2)
        binding.ivTest.getLocationOnScreen(locationArray)
        Log.d(TAG, "initView: location 0  ${locationArray[0]}  location 1 ${locationArray[1]}")

    }


}



