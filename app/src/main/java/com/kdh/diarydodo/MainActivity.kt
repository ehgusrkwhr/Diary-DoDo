package com.kdh.diarydodo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

import com.kdh.diarydodo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigation()
        //val navHostFragment = supportFragmentManager.findFragmentById()
    }

    fun initNavigation() {
        //네비게이션 담는 호스트
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        // 네비게이션 컨트롤러
        navController = navHostFragment.navController
        //바텀 네이 뷰 와 네비게이션
        NavigationUI.setupWithNavController(binding.diaryBottom, navController)
    }


}