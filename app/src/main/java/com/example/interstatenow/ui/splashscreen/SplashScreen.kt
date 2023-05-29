package com.example.interstatenow.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.interstatenow.MainActivity
import com.example.interstatenow.R
import com.example.interstatenow.ui.login.LoginActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)

        supportActionBar?.hide()

        navigateToNextPage()


    }
    private fun navigateToNextPage() {
        // Navigasikan ke halaman berikutnya (misalnya, LoginActivity)
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Menutup SplashScreenActivity agar tidak dapat dikembalikan dengan tombol back
    }
}