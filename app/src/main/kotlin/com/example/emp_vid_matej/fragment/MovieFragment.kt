package com.example.emp_vid_matej.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.emp_vid_matej.R
import com.example.emp_vid_matej.model.Movie

class MovieFragment: Fragment(R.layout.movie_fragment) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve the Movie object
        val movie: Movie = MovieFragmentArgs.fromBundle(requireArguments()).movie

        // Populate UI with Movie data
        view.findViewById<TextView>(R.id.title).text = movie.Title
        view.findViewById<TextView>(R.id.meta_ratings).text = "${movie.imdbRating}/10 Ratings"
        view.findViewById<TextView>(R.id.meta_votes).text = "${movie.imdbVotes} Votes"
        view.findViewById<TextView>(R.id.meta_release).text = "Release: ${movie.Released}"
        view.findViewById<TextView>(R.id.meta_rated).text = "Rated: ${movie.Rated}"
        view.findViewById<TextView>(R.id.meta_duration).text = "Duration: ${movie.Runtime}"

        view.findViewById<TextView>(R.id.description).text = movie.Plot

        view.findViewById<TextView>(R.id.director_name).text = movie.Director
        view.findViewById<TextView>(R.id.writer_name).text = movie.Writer
        view.findViewById<TextView>(R.id.stars_names).text = movie.Actors

        // Handle Back button click
        view.findViewById<ImageView>(R.id.backButton).setOnClickListener {
            findNavController().navigateUp()
        }

    }

}