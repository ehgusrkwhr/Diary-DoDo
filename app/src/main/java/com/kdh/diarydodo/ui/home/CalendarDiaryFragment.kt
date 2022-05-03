package com.kdh.diarydodo.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kdh.diarydodo.adapter.DiaryCalendarListAdapter
import com.kdh.diarydodo.databinding.FragmentDiaryCalendarBinding
import com.kdh.diarydodo.ui.base.BaseFragment
import com.kdh.diarydodo.ui.calendar.WeekDecorator
import com.kdh.diarydodo.ui.viewmodel.DiaryViewModel
import com.kdh.diarydodo.util.DateUtil.calendarDateEqual
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CalendarDiaryFragment : BaseFragment<FragmentDiaryCalendarBinding>() {


    private val viewModel: DiaryViewModel by viewModels()
    private val weekDecorator: DayViewDecorator by lazy {
        WeekDecorator()
    }

    //    private val listAdapter: DiaryCalendarListAdapter by lazy {
//        DiaryCalendarListAdapter(requireContext())
//    }
    private var listAdapter: DiaryCalendarListAdapter? = null
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDiaryCalendarBinding {
        return FragmentDiaryCalendarBinding.inflate(inflater, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("dodo55 ", "onViewCreated cal")
        initView()
        initCalendar()
        observeViewModel()
    }

    private fun initCalendar() {

        binding.materialCalendar.addDecorator(weekDecorator)

        binding.materialCalendar.setOnDateChangedListener { widget, date, selected ->
            //날짜 클릭시 일기 존재유무파악
            // date.
            viewModel.getEqualDateDiary(calendarDateEqual(date.toString()))

        }
    }

    private fun initView() {
        listAdapter = DiaryCalendarListAdapter(requireContext())
        binding.rvIsDiary.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = listAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.eventEqualDate.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                binding.rvIsDiary.visibility = View.VISIBLE
                binding.layoutEmptyDiary.visibility = View.GONE
                listAdapter?.submitList(it)
            } else {
                binding.rvIsDiary.visibility = View.GONE
                binding.layoutEmptyDiary.visibility = View.VISIBLE
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        listAdapter = null
        Log.d("dodo55 ", "onDestroyView cal")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("dodo55 ", "onDestroy cal")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("dodo55 ", "onDetach cal")
    }
}