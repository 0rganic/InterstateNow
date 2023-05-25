package com.example.interstatenow.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class LoginViewModel : ViewModel() {
    val errorMessage: MutableLiveData<String> = MutableLiveData()



    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}