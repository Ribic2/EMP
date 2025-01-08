package com.example.emp_vid_matej.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emp_vid_matej.R
import com.example.emp_vid_matej.adapter.CommentAdapter
import com.example.emp_vid_matej.apiService.data.reqeuest.MovieCommentRequest
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

        binding.likeButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                movieViewModel.likeMovies(movieId);
                movieViewModel.getMovieById(movieId)
            }
        })

        binding.sendButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val comment = binding.commentInput.text.toString()

                if (comment.isNotEmpty()) {
                    movieViewModel.postComment(
                        MovieCommentRequest(movieId, comment)
                    );
                    movieViewModel.getMovieById(movieId)
                    binding.commentInput.text?.clear();
                }
            }

        });

        lifecycleScope.launchWhenCreated {
            movieViewModel.movieResultsById.collectLatest {
                if (it != null) {
                    binding.title.text = it.originalTitle
                    binding.metaRatings.text = it.avgVote.toString()
                    binding.metaRelease.text = it.year.toString()
                    binding.metaVotes.text = it.votes.toString()
                    binding.metaDuration.text = it.duration + " min"
                    binding.description.text = it.description
                    binding.directorName.text = it.director
                    binding.writerName.text = it.writers.orEmpty().joinToString(", ")
                    binding.starsNames.text = it.actors.orEmpty().joinToString(", ")

                    // Set liked
                    if (it.isLiked) {
                        binding.likeButton.setIconResource(
                            R.drawable.ic_dislike
                        )
                    } else {
                        binding.likeButton.setIconResource(
                            R.drawable.ic_like
                        )
                    }

                    // Setup comments adapter
                    val commentAdapter = CommentAdapter()
                    binding.commentsRecyclerView.layoutManager =
                        LinearLayoutManager(requireContext())
                    binding.commentsRecyclerView.adapter = commentAdapter

                    commentAdapter.updateData(it.comments)
                }
            }
        }
    }
}