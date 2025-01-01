package com.example.emp_vid_matej.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val originalTitle: String?,
    val year: Int?,
    val duration: String?,
    val description: String?,
    val director: String?,
    val writers: List<String>?,
    val actors: List<String>?,
    val avgVote: Float?,
    val votes: Int?,
    val comments: List<Comment>?,
    val productionCompany: String?,
    val genresList: List<String>?
) : Parcelable
