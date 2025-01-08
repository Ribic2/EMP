package com.example.emp_vid_matej.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.emp_vid_matej.databinding.ProfileFragmentBinding
import com.example.emp_vid_matej.service.SessionManager
import com.example.emp_vid_matej.ui.main.AuthenticationActivity
import com.example.emp_vid_matej.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: ProfileFragmentBinding
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.user()

        // Observe the userResult live data
        authViewModel.userResult.observe(viewLifecycleOwner) { userResponse ->
            if (userResponse != null) {
                binding.profileName.text = userResponse.name
                binding.profileEmail.text = userResponse.email
            }
        }

        binding.logoutButton.setOnClickListener {
            authViewModel.logout()
            val intent = Intent(requireContext(), AuthenticationActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}

