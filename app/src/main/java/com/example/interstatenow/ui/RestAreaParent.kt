package com.example.interstatenow.ui

import com.example.interstatenow.RestAreaChild

data class RestAreaParent(
    val id: String? = null,
    val name: String? = null,
    val list_restArea: List<RestAreaChild>
)
