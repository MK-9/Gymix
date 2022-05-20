package com.gymix.domain.entity

data class DomainBook(
    val id: Long,
    val title: String,
    val description: String,
    val coverUri: String,
    val publisher: String,
    val price: Long,
    val PhysicalPrice: Long,
    val rating: Float,
    val authors: List<DomainAuthor>,
    val numberOfPages: Int?
)