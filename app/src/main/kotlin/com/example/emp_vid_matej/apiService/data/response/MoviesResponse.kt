package com.example.emp_vid_matej.apiService.data.response

import com.example.emp_vid_matej.apiService.data.response.pagination.PaginationLinks
import com.example.emp_vid_matej.apiService.data.response.pagination.PaginationMeta
import com.example.emp_vid_matej.model.Movie

data class MoviesResponse(
    val data: List<Movie>,
    val links: PaginationLinks,
    val meta: PaginationMeta
)
