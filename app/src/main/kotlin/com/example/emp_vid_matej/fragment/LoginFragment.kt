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

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_fragment, container, false)

        val loginButton = view.findViewById<Button>(R.id.loginButton)
        val registerText = view.findViewById<TextView>(R.id.registerText)

        loginButton.setOnClickListener {
            (activity as AuthenticationActivity).navigateToMainPage()
        }

        registerText.setOnClickListener {
            (activity as AuthenticationActivity).showRegisterFragment()
        }

        return view
    }
}
