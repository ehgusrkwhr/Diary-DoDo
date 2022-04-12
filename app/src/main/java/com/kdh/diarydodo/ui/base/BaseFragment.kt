package com.kdh.diarydodo.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    private var _binding : T? = null
    val binding get() = _binding!!

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
        _binding = null
    }


}