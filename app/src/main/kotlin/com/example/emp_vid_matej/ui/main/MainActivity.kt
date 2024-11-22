package com.example.emp_vid_matej.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.emp_vid_matej.R
import com.example.emp_vid_matej.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize NavController
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Set up BottomNavigationView with NavController
        binding.bottomNavigationView.setupWithNavController(navController)

        // Handle reselection of the same tab
        binding.bottomNavigationView.setOnItemReselectedListener { item ->
            when (item.itemId) {
                R.id.menuFragment -> {
                    // If already on menuFragment, reset the stack or refresh content
                    navController.popBackStack(R.id.menuFragment, false)
                    Log.d("MainActivity", "Reselected menuFragment and refreshed content")
                }
                R.id.movieFragment -> {
                    // If already on movieFragment, reset the stack to menuFragment
                    navController.popBackStack(R.id.menuFragment, false)
                    navController.navigate(R.id.menuFragment)
                    Log.d("MainActivity", "Reselected movieFragment, navigating back to menuFragment")
                }
            }
        }

        // Handle navigation actions when tabs are selected
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuFragment -> {
                    if (navController.currentDestination?.id != R.id.menuFragment) {
                        navController.navigate(R.id.menuFragment)
                    }
                    true
                }
                R.id.movieFragment -> {
                    if (navController.currentDestination?.id != R.id.movieFragment) {
                        navController.navigate(R.id.movieFragment)
                    }
                    true
                }
                else -> false
            }
        }
    }
}

