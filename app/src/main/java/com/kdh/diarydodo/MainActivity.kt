package com.kdh.diarydodo

import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

import com.kdh.diarydodo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.all_background_color)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigation()

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

    override fun onBackPressed() {
        super.onBackPressed()
        Log.d(TAG, "onBackPressed: ")
        finish()
    }

    override fun onStop() {
        super.onStop()
        Log.d("dodo55 ","onStop")
    }

    override fun onStart() {
        super.onStart()
        Log.d("dodo55 ","onStart")
    }
    override fun onRestart() {
        super.onRestart()
        Log.d("dodo55 ","onRestart")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("dodo55 ","onDestroy")
    }
    override fun onResume() {
        super.onResume()
        Log.d("dodo55 ","onResume")
    }

}