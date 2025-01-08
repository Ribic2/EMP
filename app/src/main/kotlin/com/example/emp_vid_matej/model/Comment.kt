package com.example.emp_vid_matej.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comment(
    val id: String,
    val comment: String,
): Parcelable
