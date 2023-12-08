package com.example.myapplication.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.AdapterRoomBinding
import com.example.myapplication.room.utils.Note

class NoteAdapter(private val notes: ArrayList<Note>, private val listener: OnAdapterListener) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(val binding: AdapterRoomBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = AdapterRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.binding.textTitle.text = note.title
        holder.binding.textTitle.setOnClickListener {
            listener.onRead(note)
        }
        holder.binding.iconEdit.setOnClickListener {
            listener.onUpdate(note)
        }
        holder.binding.iconDelete.setOnClickListener {
            listener.onDelete(note)
        }
    }

    fun setData(list: List<Note>) {
        notes.clear()
        notes.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onRead(note: Note)
        fun onUpdate(note: Note)
        fun onDelete(note: Note)
    }
}