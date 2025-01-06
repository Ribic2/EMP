package com.example.emp_vid_matej.apiService.data.reqeuest

data class MovieFilter (
    var q: String = "",
    var genres: List<String> = emptyList()
)