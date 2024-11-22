package com.example.emp_vid_matej.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.emp_vid_matej.R
import com.example.emp_vid_matej.model.Movie

class MoviesAdapter(private val movies: List<Movie>, private val onClick: (Movie) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleView: TextView = itemView.findViewById(R.id.textViewMovieTitle)
        val descriptionView: TextView = itemView.findViewById(R.id.textViewMovieDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_card_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.titleView.text = movie.Title
        holder.descriptionView.text = movie.Plot

        // Set click listener
        holder.itemView.setOnClickListener {
            onClick(movie)
        }
    }


    override fun getItemCount() = movies.size
}