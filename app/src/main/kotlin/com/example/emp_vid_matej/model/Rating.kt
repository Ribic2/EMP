package com.example.emp_vid_matej.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rating(
    val Source: String,
    val Value: String
) : Parcelable