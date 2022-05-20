package com.gymix.presentation.book.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BooksList(
    val books: List<Book>,
    val tabSeparated: Boolean,
    val spinnerItems: List<SpinnerItem>,
    val currentSpinnerPosition: Int
) : Parcelable