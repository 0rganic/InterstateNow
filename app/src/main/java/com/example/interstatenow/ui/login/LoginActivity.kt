package com.example.interstatenow.ui.login

import android.content.Intent
import android.os.Bundle

import android.util.Patterns
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.interstatenow.MainActivity
import com.example.interstatenow.R
import com.example.interstatenow.databinding.ActivityLoginBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    private lateinit var edtPassword: TextInputEditText
    private lateinit var passwordVisibilityToggle: ImageView
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()
        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            navigateToMainActivity()
            return
        }

        edtPassword = binding.edtPassword
        passwordVisibilityToggle = binding.passwordVisibilityToggle

        binding.passwordVisibilityToggle.setOnClickListener {
            togglePasswordVisibility()
        }

        binding.btnLogin.setOnClickListener {
            loginCheck()
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun togglePasswordVisibility() {
        val passwordVisible = edtPassword.inputType != (android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD)

        if (passwordVisible) {
            edtPassword.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            passwordVisibilityToggle.setImageResource(R.drawable.ic_visibility_off)
        } else {
            edtPassword.inputType = android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            passwordVisibilityToggle.setImageResource(R.drawable.ic_visibility)
        }
        edtPassword.setSelection(edtPassword.text!!.length)
    }

    private fun loginCheck() {

        val email = binding.edtEmail.text.toString().trim()
        val password = binding.edtPassword.text.toString().trim()

        if (email.isEmpty()) {
            binding.edtEmail.error = "Please enter your email."
            binding.edtEmail.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.edtEmail.error = "Please enter a valid email address."
            binding.edtEmail.requestFocus()
            return
        }

        if (password.isEmpty()) {
            binding.edtPassword.error = "Please enter your password."
            binding.edtPassword.requestFocus()
            return
        }

        login(email,password)
    }

    private fun login(email: String, password: String) {
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(this){
            if (it.isSuccessful){
                Toast.makeText(this, "login berhasil", Toast.LENGTH_SHORT).show()
                val currentUser = auth.currentUser
                currentUser?.let { user ->
                    auth.updateCurrentUser(user)
                }

                navigateToMainActivity()
            } else {
                Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}