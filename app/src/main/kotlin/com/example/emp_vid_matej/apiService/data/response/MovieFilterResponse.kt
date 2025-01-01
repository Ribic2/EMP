package com.example.emp_vid_matej.apiService.data.response

import com.example.emp_vid_matej.model.Genre

data class MovieFilterResponse(
    val genres: List<Genre>,
    val ratings: List<Int>
)
