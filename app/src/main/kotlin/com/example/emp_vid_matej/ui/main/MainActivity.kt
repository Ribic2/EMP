package com.example.emp_vid_matej.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.emp_vid_matej.R
import com.example.emp_vid_matej.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

        // Handle navigation actions when tabs are selected or reselected
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            val currentDestination = navController.currentDestination?.id
            when (item.itemId) {
                R.id.menuFragment -> {
                    handleNavigation(R.id.menuFragment, currentDestination)
                    true
                }
                R.id.profileFragment -> {
                    handleNavigation(R.id.profileFragment, currentDestination)
                    true
                }
                R.id.recommendationFragment -> {
                    handleNavigation(R.id.recommendationFragment, currentDestination)
                    true
                }
                else -> false
            }
        }
    }

    private fun handleNavigation(destinationId: Int, currentDestination: Int?) {
        if (currentDestination == destinationId) {
            navController.popBackStack(destinationId, false)
            Log.d("MainActivity", "Reselected fragment with ID: $destinationId")
        } else {
            navController.navigate(destinationId)
            Log.d("MainActivity", "Navigated to fragment with ID: $destinationId")
        }
    }
}
