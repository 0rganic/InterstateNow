package com.example.interstatenow.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.interstatenow.databinding.FragmentProfileBinding
import com.example.interstatenow.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)


        binding.btnLogout.setOnClickListener{
            logoutButtonClicked()
        }
        return binding.root
    }

    fun logoutButtonClicked() {
        FirebaseAuth.getInstance().signOut()
        // Navigasikan pengguna ke halaman login atau tampilan lain yang sesuai
        navigateToLoginPage()
    }

    private fun navigateToLoginPage() {
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}