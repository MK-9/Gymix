package com.gymix.presentation.book

import androidx.recyclerview.widget.DiffUtil
import com.gymix.domain.entity.DomainBook

class BookItemDiffUtils : DiffUtil.ItemCallback<DomainBook>() {
    override fun areItemsTheSame(oldItem: DomainBook, newItem: DomainBook): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DomainBook, newItem: DomainBook): Boolean {
        return oldItem.id == newItem.id &&
                oldItem.title == newItem.title &&
                oldItem.description == newItem.description
    }
}