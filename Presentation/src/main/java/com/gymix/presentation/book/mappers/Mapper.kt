package com.gymix.presentation.book.mappers

import com.gymix.domain.entities.*
import com.gymix.presentation.book.models.*

fun DomainBookResponse.map() = BookResponse(
    bookList = bookList.map(),
    hasMore = hasMore,
    nextOffset = nextOffset
)

fun DomainBooksList.map() = BooksList(
    books = books.map { book -> book.map() },
    tabSeparated = tabSeparated,
    spinnerItems = spinnerItems.map { spinnerItem -> spinnerItem.map() },
    currentSpinnerPosition = currentSpinnerPosition
)

fun DomainSpinnerItem.map() = SpinnerItem(id = id, title = title)

fun DomainBook.map() = Book(
    id = id,
    title = title,
    description = description,
    coverUri = coverUri,
    publisher = publisher,
    price = price,
    PhysicalPrice = PhysicalPrice,
    rating = rating,
    authors = authors.map { author -> author.map() },
    numberOfPages = numberOfPages
)

fun DomainAuthor.map() = Author(
    id = id,
    firstName = firstName,
    lastName = lastName,
    slug = slug
)


