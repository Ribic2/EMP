package com.example.emp_vid_matej.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.emp_vid_matej.adapter.CommentAdapter
import com.example.emp_vid_matej.databinding.MovieFragmentBinding
import com.example.emp_vid_matej.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

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

        lifecycleScope.launchWhenCreated {
            movieViewModel.movieResultsById.collectLatest {
                if (it != null) {
                    binding.title.text = it.originalTitle
                    binding.metaRatings.text = it.avgVote.toString()
                    binding.metaRelease.text = it.year.toString()
                    binding.metaVotes.text = it.votes.toString()
                    binding.metaDuration.text = it.duration
                    binding.description.text = it.description
                    binding.directorName.text = it.director
                    binding.writerName.text = it.writers.toString()
                    binding.starsNames.text = it.actors.toString()

                    // Setup comments adapter
                    val commentAdapter = CommentAdapter()
                    binding.commentsRecyclerView.adapter = commentAdapter
                    commentAdapter.submitList(it.comments)
                }
            }
        }
    }
}