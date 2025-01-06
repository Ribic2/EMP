package com.example.emp_vid_matej.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.emp_vid_matej.databinding.CommentCardBinding
import com.example.emp_vid_matej.model.Comment

class CommentAdapter() :
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {


    fun updateData(newComments: List<Comment>){
        comments.clear();
        comments.addAll(newComments);
        notifyDataSetChanged()
    }

    private val comments = mutableListOf<Comment>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CommentCardBinding.inflate(inflater, parent, false);
        return CommentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    class CommentViewHolder(private val binding: CommentCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment) {
            binding.comment.text = comment.comment
            binding.username.text = comment.user
        }
    }
}