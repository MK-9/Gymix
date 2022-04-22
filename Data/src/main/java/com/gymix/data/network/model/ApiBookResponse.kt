package com.gymix.data.network.model

import com.gymix.domain.entity.Book

data class ApiBookResponse(
    var bookList: List<Book>,
    var hasMore: Boolean,
    var nextOffset: String
)