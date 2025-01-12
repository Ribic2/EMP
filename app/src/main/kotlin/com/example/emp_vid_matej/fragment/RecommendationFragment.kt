package com.example.emp_vid_matej.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emp_vid_matej.R
import com.example.emp_vid_matej.adapter.MovieAdapter
import com.example.emp_vid_matej.databinding.MenuFragmentBinding
import com.example.emp_vid_matej.databinding.RecommendationFragmentBinding
import com.example.emp_vid_matej.model.Movie
import com.example.emp_vid_matej.viewmodel.MovieViewModel
import kotlinx.coroutines.flow.collectLatest


class RecommendationFragment : Fragment() {
    private lateinit var binding: RecommendationFragmentBinding
    private val movieViewModel: MovieViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RecommendationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewMovies.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewMovies.adapter = movieAdapter

        movieViewModel.getRecommended();

        lifecycleScope.launchWhenStarted {
            movieViewModel.movieResultsRecommended.collectLatest {
                if (it != null) {
                    Log.d("recommendedMovies", it.toString())

                    movieAdapter.updateData(it)
                }
            }
        }

        binding.recyclerViewMovies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieAdapter
        }

    }


    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter { movie ->
            navigateToMovieFragment(movie)
        }
    }

    private fun navigateToMovieFragment(movie: Movie) {
        val action = RecommendationFragmentDirections.actionRecommendedFragmentToMovieFragment(movie)
        findNavController().navigate(action)
    }
}