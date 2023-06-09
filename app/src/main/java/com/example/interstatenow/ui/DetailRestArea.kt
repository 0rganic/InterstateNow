package com.example.interstatenow.ui

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import com.bumptech.glide.Glide
import com.example.interstatenow.R
import com.example.interstatenow.databinding.ActivityDetailRestAreaBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.lang.System.load
import android.widget.ImageView

class DetailRestArea : AppCompatActivity() {

    companion object {
        private const val IMG = "restAreaImg"
    }
    private lateinit var binding: ActivityDetailRestAreaBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRestAreaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        val data = intent.getStringExtra(IMG)

        Log.d("foto", data.toString())

        Glide.with(this)
            .load(data)
            .into(binding.imgRestarea)


        val database = Firebase.database
        val myRef = database.getReference("count")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val post = dataSnapshot.getValue<DataRestArea>()
                Log.d("data", post.toString())
                // ...
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)

    }
}