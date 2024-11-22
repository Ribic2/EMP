package com.example.emp_vid_matej.fragment

import androidx.fragment.app.Fragment
import com.example.emp_vid_matej.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.emp_vid_matej.adapter.MoviesAdapter
import com.example.emp_vid_matej.databinding.MenuFragmentBinding
import com.example.emp_vid_matej.repository.MovieRepositoryImplementation

class MenuFragment: Fragment(R.layout.menu_fragment) {

    private var _binding: MenuFragmentBinding? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val movieRepository = MovieRepositoryImplementation()
        val view = inflater.inflate(R.layout.menu_fragment, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewMovies)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Example movie data
        val movies = movieRepository.getMovies(requireContext())


        adapter = MoviesAdapter(movies) { selectedMovie ->
            val action = MenuFragmentDirections.actionMenuFragmentToMovieFragment(selectedMovie)
            findNavController().navigate(action)
        }
        recyclerView.adapter = adapter

        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}