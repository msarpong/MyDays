package com.msarpong.mydays.ui.main

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.vipulasri.timelineview.TimelineView
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.detail.DetailScreen
import com.msarpong.mydays.utils.formatDateTime
import org.msarpong.mydays.Db.Notes


class MainAdapter :
    ListAdapter<Notes, NotesViewHolder>(NotesDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.cell_item, parent, false)
        return NotesViewHolder(cellForRow, viewType)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        val diary = getItem(position)

        holder.diaryTitle.text = diary.title
        holder.diaryDate.text = diary.datetime.formatDateTime("yyyy-MM-dd HH:mm", "H:mm")
        holder.diaryCard.setOnClickListener {
            DetailScreen.openDetail(holder.diaryTitle.context as Activity, diary.id)
        }
        holder.diaryCard.setOnLongClickListener {
            Toast.makeText(holder.diaryTitle.context, "Long click detected", Toast.LENGTH_SHORT)
                .show()
            true
        }
    }
}


class NotesViewHolder(view: View, viewType: Int) : RecyclerView.ViewHolder(view) {
    var mTimelineView: TimelineView


    init {
        mTimelineView = itemView.findViewById<View>(R.id.timeline) as TimelineView
        mTimelineView.initLine(viewType)
    }

    val diaryTitle = view.findViewById<TextView>(R.id.recycler_title)
    val diaryDate = view.findViewById<TextView>(R.id.recycler_date)
    val diaryCard = view.findViewById<CardView>(R.id.recycler_card)
}

class NotesDiffUtil : DiffUtil.ItemCallback<Notes>() {
    override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
        return oldItem == newItem
    }
}