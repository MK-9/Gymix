package com.gymix.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gymix.domain.entity.Note
import com.gymix.presentation.databinding.ItemNoteBinding

class NoteAdapter constructor(val viewModel: NoteViewModel) :
    ListAdapter<Note, NoteViewHolder>(MyDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModel, item)
    }

}
