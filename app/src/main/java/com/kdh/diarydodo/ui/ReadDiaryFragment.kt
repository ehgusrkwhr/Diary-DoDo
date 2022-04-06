package com.kdh.diarydodo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kdh.diarydodo.adapter.DiaryListAdapter
import com.kdh.diarydodo.data.DiaryInfo
import com.kdh.diarydodo.databinding.FragmentReadDiaryBinding

class ReadDiaryFragment : Fragment() {

    private var mBinding: FragmentReadDiaryBinding? = null
    private lateinit var list: ArrayList<DiaryInfo>

    //    private lateinit var list : ArrayList<DiaryInfo>
//    private val list : ArrayList<DiaryInfo> by lazy{
////        for(i in 1..10){
////            val temp = DiaryInfo()
////            list.add(temp)
////        }
//        val temp = DiaryInfo()
//        list.add(temp)
//        list
//    }
//     private val list : ArrayList<DiaryInfo> = TODO()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentReadDiaryBinding.inflate(inflater, container, false)
        mBinding = binding
        return mBinding?.root
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_read_diary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentReadDiaryBinding.inflate(layoutInflater)
        list = ArrayList()
        for (i in 1..10) {
            val temp = DiaryInfo()
            list.add(temp)
        }

        with(binding.readDiaryListView) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = DiaryListAdapter(list)
        }

    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }

}