package com.gymix.presentation.book.models

data class BookResponse(
    val bookList: BooksList,
    val hasMore: Boolean,
    val nextOffset: String
)