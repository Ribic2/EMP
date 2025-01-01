package com.example.emp_vid_matej.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.emp_vid_matej.databinding.CommentCardBinding
import com.example.emp_vid_matej.model.Comment

class CommentAdapter() : ListAdapter<Comment, CommentAdapter.CommentViewHolder>(ViewDiff())
{
    class CommentViewHolder(private val binding: CommentCardBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(comment: Comment){
            Log.d("comment", comment.toString())
            binding.comment.text = comment.comment
            binding.username.text = comment.user
        }
    }

    class ViewDiff: DiffUtil.ItemCallback<Comment>() {
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = CommentCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        Log.d("comment", "hoo")

        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        Log.d("comment", position.toString())

        holder.bind(getItem(position))
    }

}