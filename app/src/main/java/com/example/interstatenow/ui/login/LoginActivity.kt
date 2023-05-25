package com.example.interstatenow.ui.login

import android.os.Bundle

import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.interstatenow.R
import com.google.android.material.textfield.TextInputEditText


class LoginActivity : AppCompatActivity() {
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: TextInputEditText
    private lateinit var passwordVisibilityToggle: ImageView
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        passwordVisibilityToggle = findViewById(R.id.password_visibility_toggle)
        btnLogin = findViewById(R.id.btn_login)

        passwordVisibilityToggle.setOnClickListener {
            togglePasswordVisibility()
        }

        btnLogin.setOnClickListener {
            login()
        }
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

    private fun login() {
        val email = edtEmail.text.toString().trim()
        val password = edtPassword.text.toString().trim()

        if (email.isEmpty()) {
            showToast("Please enter your email.")
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("Please enter a valid email address.")
            return
        }

        if (password.isEmpty()) {
            showToast("Please enter your password.")
            return
        }

        // Perform login operation here
        // ...
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}