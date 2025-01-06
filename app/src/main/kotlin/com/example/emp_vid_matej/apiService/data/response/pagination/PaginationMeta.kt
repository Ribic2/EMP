package com.example.emp_vid_matej.apiService.data.response.pagination

data class PaginationMeta(
    val currentPage: Int?,
    val from: Int?,
    val lastPage: Int?,
    val links: List<PaginationLink>?,
    val path: String?,
    val perPage: Int?,
    val to: Int?,
    val total: Int?
){
    companion object {
        fun empty() = PaginationMeta(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
    }
}
