package com.example.emp_vid_matej.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.emp_vid_matej.R
import com.example.emp_vid_matej.ui.main.AuthenticationActivity

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.register_fragment, container, false)

        val registerButton = view.findViewById<Button>(R.id.registerButton)
        val loginText = view.findViewById<TextView>(R.id.loginText)

        registerButton.setOnClickListener {
            (activity as AuthenticationActivity).navigateToMainPage()
        }

        loginText.setOnClickListener {
            (activity as AuthenticationActivity).showLoginFragment()
        }

        return view
    }
}
