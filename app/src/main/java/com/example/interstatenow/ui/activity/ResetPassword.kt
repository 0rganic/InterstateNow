package com.example.interstatenow.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.interstatenow.R
import com.example.interstatenow.databinding.ActivityResetPasswordBinding
import com.example.interstatenow.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class ResetPassword : AppCompatActivity() {
    private lateinit var binding: ActivityResetPasswordBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar!!.hide()

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnReset.setOnClickListener{
            val resetPassword = binding.edtEmail.text.toString()

            firebaseAuth.sendPasswordResetEmail(resetPassword).addOnSuccessListener {
                Toast.makeText(this, "Please Check Your Email", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }.addOnFailureListener{
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }
        }


    }
}
