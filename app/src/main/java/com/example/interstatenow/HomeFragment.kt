package com.example.interstatenow

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interstatenow.databinding.FragmentHomeBinding
import com.example.interstatenow.ui.RestAreaParent
import com.example.interstatenow.ui.SpaceItemDecoration
import com.example.interstatenow.ui.adapter.ParentAdapter
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val parentList = mutableListOf<RestAreaParent>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.rvParentItem.layoutManager = LinearLayoutManager(requireContext())

        FirebaseApp.initializeApp(requireContext())

        val spaceWidthPx = resources.getDimensionPixelSize(R.dimen.activity_vertical_margin)
        binding.rvParentItem.addItemDecoration(SpaceItemDecoration(spaceWidthPx))
        addDataToList()

        return binding.root

    }

    private fun addDataToList() {
        val db = FirebaseFirestore.getInstance()

        // Mendapatkan koleksi (collection) dari Firestore
        val document = db.collection("test_db").document("4HSAimb8PVrHtrzO9Lt2")

        // Membaca data dari koleksi
        document.get()
            .addOnSuccessListener { result ->
                if (result != null) {
                    val data = result.data
                    // Lakukan sesuatu dengan data
                    if (data != null) {
                        val listToll = data["list_toll"] as List<Map<String, Any>>?
                        if (listToll != null) {
                            for (toll in listToll) {
                                val name = toll["name"] as String?
                                val id = toll["id"] as String?
                                val restAreaList = mutableListOf<RestAreaChild>()

                                val listRestArea = toll["list_restArea"] as List<Map<String, Any>>?
                                if (listRestArea != null) {
                                    for (restArea in listRestArea) {
                                        val restAreaName = restArea["name"] as String?
                                        val restAreaId = restArea["id_restArea"] as String?
                                        val restAreaImage = restArea["image"] as String?
                                        val restAreaChild = RestAreaChild(restAreaId, restAreaName, restAreaImage)
                                        restAreaList.add(restAreaChild)

                                        Log.d("child", restAreaChild.toString())
                                    }
                                }

                                val parent = RestAreaParent(id, name, restAreaList)
                                parentList.add(parent)
                            }
                        }
                    }
                    val adapter = ParentAdapter(parentList)
                    binding.rvParentItem.adapter = adapter
                    Log.d("parent", parentList.toString())
                } else {
                    Log.d("FirestoreData", "Document not found")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("FirestoreData", "Error getting document: $exception")
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}
