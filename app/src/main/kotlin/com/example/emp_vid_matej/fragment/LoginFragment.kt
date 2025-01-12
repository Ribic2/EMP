package com.example.emp_vid_matej.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.emp_vid_matej.R
import com.example.emp_vid_matej.viewmodel.AuthViewModel
import com.example.emp_vid_matej.databinding.LoginFragmentBinding
import com.example.emp_vid_matej.ui.main.AuthenticationActivity
import com.google.android.material.snackbar.Snackbar
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

        binding.registerText.setOnClickListener({
            (activity as AuthenticationActivity).showRegisterFragment()
        })

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
        authViewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                showMaterial3Snackbar(errorMessage)
                authViewModel.clearError() // Počisti napako, da se Snackbar ne ponavlja
            }
        }
        Snackbar.make(
            binding.root,               // View, na katerem se prikaže Snackbar
            "Napaka pri prijavi!",      // Besedilo napake
            Snackbar.LENGTH_LONG        // Dolžina prikaza
        ).apply {
            setBackgroundTint(
                ContextCompat.getColor(context, R.color.error_color) // Barva ozadja
            )
            setTextColor(
                ContextCompat.getColor(context, R.color.white)       // Barva besedila
            )
            setActionTextColor(
                ContextCompat.getColor(context, R.color.black) // Barva gumba
            )
            setAction("Retry") {
                // Dejanja, ko uporabnik klikne gumb
            }
            show()
        }

    }

    private fun navigateToHome() {
        (activity as AuthenticationActivity).navigateToMainPage()
        Toast.makeText(context, "Welcome", Toast.LENGTH_SHORT).show()
    }

    private fun showError(errorMessage: String) {
        binding.errorTextView.text = errorMessage
    }
    private fun showMaterial3Snackbar(message: String) {
        Snackbar.make(
            binding.root, // Glavni "root" view, na katerem se prikaže Snackbar
            message,
            Snackbar.LENGTH_LONG // Čas prikaza
        ).apply {
            setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.error_color))
            setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
            setAction("Retry") {
                authViewModel.user() // Ponovi zahtevo za uporabniške podatke
            }
            show()
        }
    }
}
