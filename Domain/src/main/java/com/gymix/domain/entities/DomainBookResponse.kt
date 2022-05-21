package com.gymix.domain.entities

data class DomainBookResponse(
    val bookList: DomainBooksList,
    val hasMore: Boolean,
    val nextOffset: String
)