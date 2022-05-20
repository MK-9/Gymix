package com.gymix.data.repository

import com.gymix.data.dto.BookDataSource
import com.gymix.domain.repository.BookRepository
import com.gymix.common.Result
import com.gymix.domain.entities.DomainBook

class DefaultBookRepository(private val dataSource: BookDataSource) : BookRepository {

    override suspend fun getBook(): Result<List<DomainBook>> {
        val response = dataSource.fetchBooks()

        if (response.isSuccessful) {

            response.body()?.run {
                return Result.OnSuccess(bookList.books)
            }

            return Result.OnError("unSuccessFul Response")
        }

        return Result.OnError("error")
    }

//    override suspend fun toggleBookMark(bookId: Int): Flow<Set<String>> {
//        return
//    }
}