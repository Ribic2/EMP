package com.example.emp_vid_matej.ApiService.data.response

data class PaginationMeta(
    val currentPage: Int,
    val from: Int,
    val lastPage: Int,
    val links: List<PaginationLink>,
    val path: String,
    val perPage: Int,
    val to: Int,
    val total: Int
)
