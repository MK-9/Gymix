package com.gymix.domain.entity

data class DomainBookResponse(
    val bookList: DomainBooksList,
    val hasMore: Boolean,
    val nextOffset: String
)