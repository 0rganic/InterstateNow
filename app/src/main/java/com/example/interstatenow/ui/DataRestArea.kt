package com.example.interstatenow.ui

import com.google.firebase.database.PropertyName

data class DataRestArea(
    val capacity: Long? = null,
    @get:PropertyName("in")
    @set:PropertyName("in")
    var inValue: Long? = null,
    val out: Long? = null
)
