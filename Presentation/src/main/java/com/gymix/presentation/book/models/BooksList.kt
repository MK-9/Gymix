package com.gymix.presentation.book.models

data class BooksList(
    val books: List<Book>,
    val tabSeparated: Boolean,
    val spinnerItems: List<SpinnerItem>,
    val currentSpinnerPosition: Int
)