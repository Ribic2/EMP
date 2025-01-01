package com.example.emp_vid_matej.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.emp_vid_matej.R
import com.example.emp_vid_matej.fragment.LoginFragment
import com.example.emp_vid_matej.fragment.RegisterFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, LoginFragment())
            .commit()
    }

    fun navigateToMainPage() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun showRegisterFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, RegisterFragment())
            .addToBackStack(null)
            .commit()
    }

    fun showLoginFragment() {
        supportFragmentManager.popBackStack()
    }
}