package com.example.interstatenow.ui

import com.example.interstatenow.RestAreaChild

data class RestAreaParent(
    val id: String? = null,
    val toll_name: String? = null,
    val toll_number: String? = null,
    val mList: List<RestAreaChild>
)
