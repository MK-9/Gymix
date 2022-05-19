package com.gymix.presentation.book

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gymix.domain.entity.Book
import com.gymix.presentation.databinding.ItemBookListBinding

class BookListAdapter : ListAdapter<Book, BookListAdapter.BookItemViewHolder>(BookItemDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        val binding = ItemBookListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BookItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        val book: Book? = getItem(position)
        book?.run {
            holder.bind(book, position)
        }
    }


    /**
     * View Holder Class
     */
    inner class BookItemViewHolder(private val binding: ItemBookListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book, position: Int) {
            binding.txtTitle.text = book.title
            binding.txtDescription.text = book.description
        }
    }


}