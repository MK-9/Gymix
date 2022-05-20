package com.gymix.domain.entity

data class DomainBooksList(
    val books: List<DomainBook>,
    val tabSeparated: Boolean,
    val spinnerItems: List<DomainSpinnerItem>,
    val currentSpinnerPosition: Int
)