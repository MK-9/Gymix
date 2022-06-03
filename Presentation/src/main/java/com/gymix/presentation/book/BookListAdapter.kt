package com.gymix.presentation.book

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gymix.presentation.R
import com.gymix.presentation.book.models.Book
import com.gymix.presentation.databinding.SmallItemBookListBinding

class BookListAdapter : ListAdapter<Book, BookListAdapter.BookItemViewHolder>(BookItemDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        val binding = SmallItemBookListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val gridManager = GridManager(parent.context)
        val layoutParams = ViewGroup.LayoutParams(
            gridManager.getBoxItemWidth().toInt(),
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        binding.root.layoutParams = layoutParams
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
    inner class BookItemViewHolder(private val binding: SmallItemBookListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book, position: Int) {
            binding.txtTitle.text = book.title
            binding.imgCover.setImageResource(R.drawable.ic_baseline_collections_bookmark_48)
//            binding.txtDescription.text = book.description
        }
    }


}