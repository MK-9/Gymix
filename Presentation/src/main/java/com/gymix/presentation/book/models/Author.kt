package com.gymix.presentation.book.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Author(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val slug: String
):Parcelable