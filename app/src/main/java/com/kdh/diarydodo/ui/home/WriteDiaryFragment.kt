package com.kdh.diarydodo.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kdh.diarydodo.R
import com.kdh.diarydodo.data.db.DiaryDataBase
import com.kdh.diarydodo.databinding.FragmentReadDiaryBinding
import com.kdh.diarydodo.databinding.FragmentWriteDiaryBinding
import kotlinx.android.synthetic.main.fragment_write_diary.view.*


class WriteDiaryFragment : Fragment() {

    lateinit var db : DiaryDataBase
    private var mBinding: FragmentWriteDiaryBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        db = context?.let { DiaryDataBase.getInstance(it) }!!
     //   return inflater.inflate(R.layout.fragment_write_diary, container, false)

        val binding = FragmentWriteDiaryBinding.inflate(inflater, container, false)
        mBinding = binding
        return mBinding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentWriteDiaryBinding.inflate(layoutInflater)

        binding.diaryReg.setOnClickListener{
            
        }


    }



}