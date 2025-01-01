package com.example.emp_vid_matej.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.emp_vid_matej.viewmodel.AuthViewModel
import com.example.emp_vid_matej.databinding.LoginFragmentBinding
import com.example.emp_vid_matej.ui.main.AuthenticationActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Check fi user is logged in already or not
        authViewModel.user()
        authViewModel.userResult.observe(viewLifecycleOwner) { userResponse ->
            if (userResponse != null) {
                (activity as AuthenticationActivity).navigateToMainPage()
            }
        }

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            authViewModel.login(email, password)
        }

        authViewModel.loginResult.observe(viewLifecycleOwner) { result ->
            result?.let {
                navigateToHome()
            }
        }

        authViewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                showError(it)
            }
        }
    }

    private fun navigateToHome() {
        (activity as AuthenticationActivity).navigateToMainPage()
        Toast.makeText(context, "Welcome", Toast.LENGTH_SHORT).show()
    }

    private fun showError(errorMessage: String) {
        binding.errorTextView.text = errorMessage
    }
}
