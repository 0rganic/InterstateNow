package com.example.interstatenow.ui.fragment

import HomeViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interstatenow.R
import com.example.interstatenow.databinding.FragmentHomeBinding
import com.example.interstatenow.response.RestAreaChild
import com.example.interstatenow.response.RestAreaParent
import com.example.interstatenow.ui.SpaceItemDecoration
import com.example.interstatenow.ui.adapter.ParentAdapter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.rvParentItem.layoutManager = LinearLayoutManager(requireContext())

        val spaceWidthPx = resources.getDimensionPixelSize(R.dimen.activity_vertical_margin)
        binding.rvParentItem.addItemDecoration(SpaceItemDecoration(spaceWidthPx))

        searchView = binding.searchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        homeViewModel.parentList.observe(viewLifecycleOwner) { parentList ->
            val adapter = ParentAdapter(parentList)
            binding.rvParentItem.adapter = adapter
        }

        savedInstanceState?.let {
            val restoredList = it.getParcelableArrayList<RestAreaParent>("parentList")
            restoredList?.let { it1 -> homeViewModel.setParentList(it1) }
        } ?: run {
            homeViewModel.fetchData()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("parentList", ArrayList(homeViewModel.parentList.value))
    }

    private fun filterList(query: String?) {
        val filteredList = homeViewModel.filterList(homeViewModel.parentList.value.orEmpty(), query)
        if (filteredList.isEmpty()) {
            Toast.makeText(requireContext(), "No Data found", Toast.LENGTH_SHORT).show()
        } else {
            val adapter = ParentAdapter(filteredList)
            binding.rvParentItem.adapter = adapter
            adapter.setFilteredList(filteredList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}