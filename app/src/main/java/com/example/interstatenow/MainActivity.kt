package com.example.interstatenow
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        bottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigationView.setSelectedItemId(R.id.bottom_home)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, HomeFragment())
            .commit()

        bottomNavigationView.setOnItemSelectedListener { item ->
            val fragment: Fragment? = when (item.itemId) {
                R.id.bottom_home -> HomeFragment()
                R.id.bottom_location -> LocationFragment()
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
}