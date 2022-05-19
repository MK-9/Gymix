package com.gymix.data.network.model

import com.google.gson.annotations.SerializedName
import com.gymix.domain.entity.Book

data class NetworkBook(
    @SerializedName("bookList")
    val bookList: BooksList,
    @SerializedName("hasMore")
    val hasMore: Boolean,
    @SerializedName("nextOffset")
    val nextOffset: String
)

data class BooksList(
    @SerializedName("books")
    val books: List<Book>,
    @SerializedName("tabSeparated")
    val tabSeparated: Boolean,
    @SerializedName("spinnerItems")
    val spinnerItems: List<SpinnerItem>,
    @SerializedName("currentSpinnerPosition")
    val currentSpinnerPosition: Int
)

data class SpinnerItem(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String
)