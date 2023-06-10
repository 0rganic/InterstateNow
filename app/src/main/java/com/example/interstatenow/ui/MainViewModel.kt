//package com.example.interstatenow.ui
//
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.example.interstatenow.response.RestAreaChild
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class MainViewModel {
//
//    companion object {
//        private const val TAG = "MainViewModel"
//        private const val Q = "jeremy"
//    }
//
//    private val _toll = MutableLiveData<List<RestAreaParent>>()
//    val toll: LiveData<List<RestAreaParent>> = _toll
//
//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading
//
//    private val _restArea = MutableLiveData<RestAreaChild>()
//    val restArea: LiveData<RestAreaChild> = _restArea
//
//
//    init {
//        findtoll()
//        setQuery(query)
//    }
//
//    fun setQuery(newQuery: String) {
//        query = newQuery
//        findtoll()
//    }
//
//}