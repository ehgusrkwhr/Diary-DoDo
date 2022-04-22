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
import com.kdh.diarydodo.databinding.FragmentDiaryCalendarBinding
import com.kdh.diarydodo.databinding.FragmentReadDiaryBinding
import com.kdh.diarydodo.helper.ItemTouchHelperCallback
import com.kdh.diarydodo.ui.base.BaseFragment
import com.kdh.diarydodo.ui.calendar.WeekDecorator
import com.kdh.diarydodo.ui.viewmodel.DiaryViewModel
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_diary_calendar.*


class CalendarDiaryFragment : BaseFragment<FragmentDiaryCalendarBinding>() {


    private val viewModel: DiaryViewModel by viewModels()
    private val weekDecorator : DayViewDecorator by lazy{
        WeekDecorator()
    }
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDiaryCalendarBinding {
        return FragmentDiaryCalendarBinding.inflate(inflater, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCalendar()
    }

    private fun initCalendar(){
        materialCalendar.addDecorator(weekDecorator)

        materialCalendar.setOnDateChangedListener{widget,date,selected ->
            //날짜 클릭시 일기 존재유무파악
           // date.
            Log.d("dodo55 ","widget ${widget}")
            Log.d("dodo55 ","date ${date}")
            Log.d("dodo55 ","selected ${selected}")

        }
    }



}