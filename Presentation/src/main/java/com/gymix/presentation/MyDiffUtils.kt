package com.gymix.presentation

import androidx.recyclerview.widget.DiffUtil
import com.gymix.domain.entity.Note

class MyDiffUtils : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}