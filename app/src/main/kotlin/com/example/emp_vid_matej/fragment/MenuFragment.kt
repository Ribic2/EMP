package com.example.emp_vid_matej.fragment

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emp_vid_matej.adapter.MovieAdapter
import com.example.emp_vid_matej.apiService.data.reqeuest.MovieFilter
import com.example.emp_vid_matej.databinding.MenuFragmentBinding
import com.example.emp_vid_matej.model.Movie

import com.example.emp_vid_matej.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MenuFragment : Fragment() {

    private lateinit var binding: MenuFragmentBinding
    private val movieViewModel: MovieViewModel by activityViewModels()
    private val searchHandler = Handler(Looper.getMainLooper())
    private val searchRunnable = Runnable {
        val newFilter = movieViewModel.currentFilter.value.copy(
            q = binding.filterInput.text.toString()
        )
        movieViewModel.getMovies(newFilter)
    }


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

        movieViewModel.getMovies(movieViewModel.currentFilter.value)

        binding.filterInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                searchHandler.removeCallbacks(searchRunnable)
                searchHandler.postDelayed(searchRunnable, 500)
            }
        })

        lifecycleScope.launchWhenStarted {
            movieViewModel.movieResults.collectLatest {
                movieAdapter.updateData(it.data)
            }
        }

        binding.recyclerViewMovies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieAdapter
        }

        binding.toggleBottomSheet.setOnClickListener {
            val bottomSheet = BottomSheetDialogFilterFragment()
            bottomSheet.show(childFragmentManager, BottomSheetDialogFilterFragment.TAG)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        searchHandler.removeCallbacks(searchRunnable)
    }

    private fun navigateToMovieFragment(movie: Movie) {
        val action = MenuFragmentDirections.actionMenuFragmentToMovieFragment(movie)
        findNavController().navigate(action)
    }

}