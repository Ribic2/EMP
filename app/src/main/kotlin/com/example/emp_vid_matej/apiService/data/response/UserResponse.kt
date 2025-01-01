package com.example.emp_vid_matej.apiService.data.response

import com.example.emp_vid_matej.model.Actions
import com.example.emp_vid_matej.model.User

data class UserResponse(
    val user: User,
    val actions: Actions
)