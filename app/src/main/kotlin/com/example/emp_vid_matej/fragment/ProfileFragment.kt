package com.example.emp_vid_matej.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.emp_vid_matej.R
import com.example.emp_vid_matej.databinding.ProfileFragmentBinding

class ProfileFragment : Fragment(R.layout.profile_fragment) {

    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ProfileFragmentBinding.bind(view)

        // Set up the logout button
        binding.logoutButton.setOnClickListener {
            // Navigate to AuthenticationActivity (which contains loginFragment)
            val intent = Intent(requireContext(), AuthenticationActivity::class.java)

            // Clear the back stack
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

