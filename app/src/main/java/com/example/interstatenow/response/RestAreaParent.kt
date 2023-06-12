package com.example.interstatenow.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.example.interstatenow.response.RestAreaChild

@Parcelize
data class RestAreaParent(
    val id: String? = null,
    val name: String? = null,
    val list_restArea: List<RestAreaChild>
): Parcelable
