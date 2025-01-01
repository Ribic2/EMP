package com.example.emp_vid_matej.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.emp_vid_matej.adapter.CommentAdapter
import com.example.emp_vid_matej.databinding.MovieFragmentBinding
import com.example.emp_vid_matej.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import hilt_aggregated_deps._com_example_emp_vid_matej_fragment_MovieFragment_GeneratedInjector

@AndroidEntryPoint
class MovieFragment : Fragment() {
    private val args: MovieFragmentArgs by navArgs()
    private lateinit var binding: MovieFragmentBinding
    private val movieViewModel: MovieViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = args.movie.id

        movieViewModel.getMovieById(movieId)

        binding.backButtonText.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val action = MovieFragmentDirections.actionMovieFragmentToMenuFragment()

                findNavController().navigate(action)
            }
        })

        movieViewModel.movieResultsById.observe(viewLifecycleOwner) { movie ->
            if (movie != null) {
                binding.title.text = movie.originalTitle
                binding.metaRatings.text = movie.avgVote.toString()
                binding.metaRelease.text = movie.year.toString()
                binding.metaVotes.text = movie.votes.toString()
                binding.metaDuration.text = movie.duration
                binding.description.text = movie.description
                binding.directorName.text = movie.director
                binding.writerName.text = movie.writers.toString()
                binding.starsNames.text = movie.actors.toString()

                // Setup comments adapter
                val commentAdapter = CommentAdapter()
                binding.commentsRecyclerView.adapter = commentAdapter
                commentAdapter.submitList(movie.comments)
            }
        }

    }

}