package com.example.emp_vid_matej.fragment

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emp_vid_matej.adapter.MovieAdapter
import com.example.emp_vid_matej.databinding.MenuFragmentBinding
import com.example.emp_vid_matej.model.Movie

import com.example.emp_vid_matej.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment() {

    private lateinit var binding: MenuFragmentBinding
    private val movieViewModel: MovieViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MenuFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter { movie ->
            navigateToMovieFragment(movie)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewMovies.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewMovies.adapter = movieAdapter

        if (movieViewModel.movieResults.value == null) {
            movieViewModel.getMovies()
        }

        movieViewModel.movieResults.observe(viewLifecycleOwner) { movieResults ->
            if (movieResults != null) {
                movieAdapter.updateData(movieResults.data)
            }
        }

        binding.recyclerViewMovies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieAdapter
        }
    }

    private fun navigateToMovieFragment(movie: Movie) {
        val action = MenuFragmentDirections.actionMenuFragmentToMovieFragment(movie)
        findNavController().navigate(action)
    }

}