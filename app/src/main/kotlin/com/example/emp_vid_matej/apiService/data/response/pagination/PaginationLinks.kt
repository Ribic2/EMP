package com.example.emp_vid_matej.apiService.data.response.pagination

data class PaginationLinks(
    val first: String?,
    val last: String?,
    val prev: String?,
    val next: String?
) {
    companion object {
        fun empty() = PaginationLinks(null, null, null, null)
    }
}