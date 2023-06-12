package com.example.interstatenow.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RestAreaChild(
    val id_restArea: String? = null,
    val name: String? = null,
    val image: String? = null
): Parcelable
