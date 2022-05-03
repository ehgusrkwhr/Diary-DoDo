package com.kdh.diarydodo.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.kdh.diarydodo.MainActivity
import com.kdh.diarydodo.R
import com.kdh.diarydodo.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //CommonUtil.setStatusBarColor(window, R.color.color_splash)
        window.statusBarColor = ContextCompat.getColor(this, R.color.splash_color)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageView.performClick();
            Handler(mainLooper).postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }, 3000)


//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
//        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}