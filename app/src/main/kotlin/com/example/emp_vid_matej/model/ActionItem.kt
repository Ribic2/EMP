package com.example.emp_vid_matej.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ActionItem(
    val id: Int,
    val name: String
): Parcelable