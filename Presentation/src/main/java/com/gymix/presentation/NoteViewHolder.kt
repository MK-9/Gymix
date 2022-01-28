package com.gymix.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gymix.domain.entity.Note
import com.gymix.presentation.databinding.ItemNoteBinding

class NoteViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(viewModel: NoteViewModel, note: Note) {
        binding.note = note
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): NoteViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemNoteBinding.inflate(inflater)
            return NoteViewHolder(binding)
        }
    }
}