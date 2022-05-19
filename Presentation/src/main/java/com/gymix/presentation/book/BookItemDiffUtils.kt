package com.gymix.presentation.book

import androidx.recyclerview.widget.DiffUtil
import com.gymix.domain.entity.Book

class BookItemDiffUtils : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.id == newItem.id &&
                oldItem.title == newItem.title &&
                oldItem.description == newItem.description
    }
}