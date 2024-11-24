package com.example.emp_vid_matej.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.emp_vid_matej.R
import com.example.emp_vid_matej.fragment.LoginFragment
import com.example.emp_vid_matej.fragment.RegisterFragment

class AuthenticationActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        // Load LoginFragment by default
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, LoginFragment())
            .commit()
    }

    fun navigateToMainPage() {
        // Navigate to MainPageActivity (Replace with your main activity class)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun showRegisterFragment() {
        // Switch to RegisterFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, RegisterFragment())
            .addToBackStack(null)
            .commit()
    }

    fun showLoginFragment() {
        // Switch to LoginFragment
        supportFragmentManager.popBackStack() // Remove RegisterFragment from back stack
    }
}