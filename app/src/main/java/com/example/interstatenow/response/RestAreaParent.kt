package com.example.interstatenow.response

import com.example.interstatenow.response.RestAreaChild

data class RestAreaParent(
    val id: String? = null,
    val name: String? = null,
    val list_restArea: List<RestAreaChild>
)
