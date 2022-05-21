package com.gymix.domain.entities

data class DomainBooksList(
    val books: List<DomainBook>,
    val tabSeparated: Boolean,
    val spinnerItems: List<DomainSpinnerItem>,
    val currentSpinnerPosition: Int
)