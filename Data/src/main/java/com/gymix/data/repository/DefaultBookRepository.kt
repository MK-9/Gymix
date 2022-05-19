package com.gymix.data.repository

import com.gymix.data.dto.BookDataSource
import com.gymix.domain.entity.Book
import com.gymix.domain.repository.BookRepository
import com.gymix.common.Result

class DefaultBookRepository(private val dataSource: BookDataSource) : BookRepository {

    override suspend fun getBook(): Result<List<Book>> {
        val response = dataSource.fetchBooks()

        if (response.isSuccessful) {

            response.body()?.run {
                return Result.OnSuccess(bookList.books)
            }

            return Result.OnError("unSuccessFul Response")
        }

        return Result.OnError("error")
    }
}