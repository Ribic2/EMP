package com.example.emp_vid_matej.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.emp_vid_matej.databinding.ItemMovieBinding
import com.example.emp_vid_matej.model.Movie

class MovieAdapter(
    private val onMovieClicked: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movies = mutableListOf<Movie>()

    fun updateData(newMovies: List<Movie>) {
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding, onMovieClicked)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    class MovieViewHolder(
        private val binding: ItemMovieBinding,
        private val onMovieClicked: (Movie) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.tvMovieTitle.text = movie.originalTitle
            binding.tvMovieDescription.text = movie.description

            binding.root.setOnClickListener {
                onMovieClicked(movie)
            }
        }
    }
}
