package com.example.interstatenow.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.interstatenow.R
import com.example.interstatenow.ui.fragment.DataGraphFragment
import com.example.interstatenow.ui.fragment.HomeFragment
import com.example.interstatenow.ui.fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private var selectedItemId: Int = R.id.bottom_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        bottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigationView.selectedItemId = R.id.bottom_home

        selectedItemId = savedInstanceState?.getInt("selectedItemId", R.id.bottom_home) ?: R.id.bottom_home
        bottomNavigationView.selectedItemId = selectedItemId

        bottomNavigationView.setOnItemSelectedListener { item ->
            selectedItemId = item.itemId

            val fragment: Fragment? = when (item.itemId) {
                R.id.bottom_home -> HomeFragment()
                R.id.bottom_graph -> DataGraphFragment()
                R.id.bottom_profile -> ProfileFragment()
                else -> null
            }

            if (fragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit()
            }

            true
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("selectedItemId", selectedItemId)
    }
}