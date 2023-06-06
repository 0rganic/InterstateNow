package com.example.interstatenow

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.interstatenow.databinding.FragmentHomeBinding
import com.example.interstatenow.ui.RestAreaParent
import com.example.interstatenow.ui.SpaceItemDecoration
import com.example.interstatenow.ui.adapter.ParentAdapter
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore



class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val parentList = ArrayList<RestAreaParent>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.rvParentItem.layoutManager = LinearLayoutManager(requireContext())

        FirebaseApp.initializeApp(requireContext())

        val adapter = ParentAdapter(parentList)
        binding.rvParentItem.adapter = adapter

        val spaceWidthPx = resources.getDimensionPixelSize(R.dimen.activity_vertical_margin)
        binding.rvParentItem.addItemDecoration(SpaceItemDecoration(spaceWidthPx))
        addDataToList()

        return binding.root

    }

    private fun addDataToList() {

        val childItems1 = ArrayList<RestAreaChild>()
        childItems1.add(RestAreaChild("1", "KM 6", R.drawable.jagorawi, "1"))
        childItems1.add(RestAreaChild("2", "KM 14", R.drawable.jagorawi, "1"))
        childItems1.add(RestAreaChild("3", "KM 30", R.drawable.jagorawi, "1"))
        childItems1.add(RestAreaChild("4", "KM 50", R.drawable.jagorawi, "1"))

        parentList.add(RestAreaParent("1","Rest Area Tol Jakarta - Cikampek", childItems1 ))

        val childItems2 = ArrayList<RestAreaChild>()
        childItems2.add(RestAreaChild("5", "KM 6", R.drawable.jagorawi, "2"))
        childItems2.add(RestAreaChild("6", "KM 14", R.drawable.jagorawi, "2"))
        childItems2.add(RestAreaChild("7", "KM 30", R.drawable.jagorawi, "2"))
        childItems2.add(RestAreaChild("8", "KM 50", R.drawable.jagorawi, "2"))

        parentList.add(RestAreaParent("2", "Rest Area Tol Batang - Semarang", childItems2))

        val childItems3 = ArrayList<RestAreaChild>()
        childItems3.add(RestAreaChild("9", "KM 6", R.drawable.jagorawi, "3"))
        childItems3.add(RestAreaChild("10", "KM 14", R.drawable.jagorawi, "3"))
        childItems3.add(RestAreaChild("11", "KM 30", R.drawable.jagorawi, "3"))
        childItems3.add(RestAreaChild("12", "KM 50", R.drawable.jagorawi, "3"))

        parentList.add(RestAreaParent("3", "Rest Area Tol Surabaya - Malang", childItems3))




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

        val db = FirebaseFirestore.getInstance()

        // Mendapatkan koleksi (collection) dari Firestore
        val collectionRef = db.collection("db")

        // Membaca data dari koleksi
        collectionRef.get()
            .addOnSuccessListener { result ->
                val list1 = ArrayList<RestAreaParent>()
                val list2 = ArrayList<RestAreaChild>()
                for (document in result) {
                    // Mengakses data dari setiap dokumen
                    val data = document.data
                    // Lakukan sesuatu dengan data
                    Log.d("FirestoreData", data.toString())
                }
            }
            .addOnFailureListener { exception ->
                Log.e("FirestoreData", "Error getting documents: $exception")
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}