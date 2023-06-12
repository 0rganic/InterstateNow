package com.example.interstatenow.ui.activity

import HomeViewModel
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.interstatenow.R
import com.example.interstatenow.databinding.ActivityDetailRestAreaBinding
import com.example.interstatenow.response.DataRestArea
import com.example.interstatenow.response.RestAreaChild
import com.example.interstatenow.response.RestAreaParent
import com.example.interstatenow.ui.SpaceItemDecoration
import com.example.interstatenow.ui.adapter.ChildAdapter
import com.example.interstatenow.ui.fragment.HomeFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class DetailRestArea : AppCompatActivity() {

    companion object {
        private const val IMG = "restAreaImg"
        private const val ID = "restAreaID"
        private const val NAME = "restAreaName"

    }

    private lateinit var binding: ActivityDetailRestAreaBinding
    private lateinit var mainViewModel: HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRestAreaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        binding.rvRestAreaDetail.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val spaceWidthPx = resources.getDimensionPixelSize(R.dimen.activity_vertical_margin)
        binding.rvRestAreaDetail.addItemDecoration(SpaceItemDecoration(spaceWidthPx))


        mainViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        mainViewModel.fetchData()
        mainViewModel.parentList.observe(this) {items ->
            setData(items)
        }

        val img = intent.getStringExtra(IMG)
        val name = intent.getStringExtra(NAME)

        binding.tvNameRestArea.text = name

        Glide.with(this)
            .load(img)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(13)))
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

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setData(items: List<RestAreaParent>?) {

        val tolID = intent.getStringExtra(ID)
        val nameToExclude = intent.getStringExtra(NAME)

        val filteredData = items?.find { it.id == tolID }?.list_restArea
            ?.filter { it.name != nameToExclude }

        val filteredNames = filteredData?.map { it.name }
        Log.d("Filtered Names", filteredNames.toString())

        val adapter = filteredData?.let { ChildAdapter(it) }
        binding.rvRestAreaDetail.adapter = adapter
    }
}
