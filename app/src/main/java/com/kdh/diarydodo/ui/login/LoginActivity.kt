package com.kdh.diarydodo.ui.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.kdh.diarydodo.MainActivity
import com.kdh.diarydodo.R
import com.kdh.diarydodo.databinding.ActivityLoginBinding
import com.kdh.diarydodo.databinding.ActivitySplashBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //CommonUtil.setStatusBarColor(window, R.color.color_splash)
        window.statusBarColor = ContextCompat.getColor(this, R.color.splash_color)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
//        finish()
    }



    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}