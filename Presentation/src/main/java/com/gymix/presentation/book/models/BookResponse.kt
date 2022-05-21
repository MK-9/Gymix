package com.gymix.presentation.book.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookResponse(
    val bookList: BooksList,
    val hasMore: Boolean,
    val nextOffset: String
) : Parcelable