package com.msarpong.mydays.ui.main

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.detail.DetailScreen
import org.msarpong.mydays.Db.Notes

class MainAdapter :
    ListAdapter<Notes, NotesViewHolder>(NotesDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.cell_item, parent, false)
        return NotesViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val diary = getItem(position)
        holder.diaryTitle.text = diary.title
        holder.diaryTitle.setOnClickListener {
            DetailScreen.openDetail(holder.diaryTitle.context as Activity, diary.id)
        }
        holder.diaryTitle.setOnLongClickListener{
            Toast.makeText(holder.diaryTitle.context, "Long click detected", Toast.LENGTH_SHORT).show()
            true
        }
    }
}

class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val diaryTitle = view.findViewById<TextView>(R.id.Tv_title)
}

class NotesDiffUtil : DiffUtil.ItemCallback<Notes>() {
    override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
        return oldItem == newItem
    }

}