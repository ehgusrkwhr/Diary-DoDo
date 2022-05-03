package com.kdh.diarydodo.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.kdh.diarydodo.R

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    private var _binding : T? = null
    val binding get() = _binding!!
    var temp1 : Int = 0
    var temp2 : Int? = 0
    var temp3 : Int = 0
    // swipe_view 를 swipe 했을 때 <삭제> 화면이 보이도록 고정하기 위한 변수들
    private var currentPosition: Int? = null    // 현재 선택된 recycler view의 position
    private var previousPosition: Int? = null   // 이전에 선택했던 recycler view의 position
    private var currentDx = 0f                  // 현재 x 값
    private var clamp = 0f                      // 고정시킬 크기



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getFragmentBinding(inflater,container)
        Log.d("dodo22 ","BaseFragment 타입 : ${binding.javaClass.name}")
        return binding.root
    }

    abstract fun getFragmentBinding(inflater : LayoutInflater,container: ViewGroup?) : T

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("dodo55 ","onDestroyView base")
        _binding = null
    }

    // view가 swipe 되었을 때 고정될 크기 설정
    fun setClamp(clamp: Float) { this.clamp = clamp }


}