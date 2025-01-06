package com.example.emp_vid_matej.apiService.data.response

import com.example.emp_vid_matej.apiService.data.response.pagination.PaginationLinks
import com.example.emp_vid_matej.apiService.data.response.pagination.PaginationMeta
import com.example.emp_vid_matej.model.Movie

data class MoviesResponse(
    val data: List<Movie> = emptyList(),
    val filters: Filters? = null
)

data class Filters(
    val applied: AppliedFilters? = null,
    val available: AvailableFilters? = null
)

data class AppliedFilters(
    val q: String? = null,
    val genres: List<String>? = null,
    val rating: String? = null
)

data class AvailableFilters(
    val genres: List<String>? = null
)