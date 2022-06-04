package com.gymix.presentation.book

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gymix.common.utils.UiUtils
import com.gymix.domain.entities.DomainBook
import com.gymix.presentation.R
import com.gymix.presentation.book.models.Book
import com.gymix.presentation.databinding.ItemBookListBinding
import com.gymix.presentation.databinding.SmallItemBookListBinding

class GridBookListAdapter(private val gridManager: GridManager) : ListAdapter<Book, GridBookListAdapter.BookItemViewHolder>(BookItemDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        val binding = SmallItemBookListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val layoutParams = ViewGroup.LayoutParams(
            gridManager.getGridItemWidth(),
            parent.context.resources.getDimensionPixelOffset(R.dimen.item_book_list_height)
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