package com.example.interstatenow.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.interstatenow.R
import com.example.interstatenow.databinding.ActivityDetailRestAreaBinding

class DetailRestArea : AppCompatActivity() {
    private lateinit var binding: ActivityDetailRestAreaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRestAreaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
    }
}