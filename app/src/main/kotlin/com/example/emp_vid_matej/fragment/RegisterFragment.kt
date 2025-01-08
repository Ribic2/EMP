package com.example.emp_vid_matej.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.emp_vid_matej.apiService.data.reqeuest.RegisterRequest
import com.example.emp_vid_matej.databinding.RegisterFragmentBinding
import com.example.emp_vid_matej.ui.main.AuthenticationActivity
import com.example.emp_vid_matej.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: RegisterFragmentBinding;
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RegisterFragmentBinding.inflate(inflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.loginText.setOnClickListener {
            (activity as AuthenticationActivity).showLoginFragment()
        }

        binding.registerButton.setOnClickListener{
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val username = binding.usernameEditText.text.toString();

            val request = RegisterRequest(email, username, password);

            authViewModel.register(request)
        }

        authViewModel.registerResult.observe(viewLifecycleOwner) { result ->
            result?.let {
                navigateToHome()
            }
        }
    }

    private fun navigateToHome() {
        (activity as AuthenticationActivity).navigateToMainPage()
        Toast.makeText(context, "Welcome", Toast.LENGTH_SHORT).show()
    }
}
