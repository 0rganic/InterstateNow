package com.example.interstatenow.ui

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.interstatenow.MainActivity
import com.example.interstatenow.databinding.ActivityDetailRestAreaBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


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


        Glide.with(this)
            .load(data)
            .into(binding.imgRestarea)


        val database = Firebase.database
        val myRef = database.getReference("count")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val post = dataSnapshot.getValue<DataRestArea>()

                post?.let { dataRestArea ->
                    binding.capacityData.text = dataRestArea.capacity.toString()
                    binding.inData.text = dataRestArea.inValue.toString()
                    binding.outData.text = dataRestArea.out.toString()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)

        binding.btnBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
