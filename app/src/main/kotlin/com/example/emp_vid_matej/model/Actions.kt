package com.example.emp_vid_matej.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Actions(
    val likes: List<ActionItem>,
    val favourites: List<ActionItem>
): Parcelable