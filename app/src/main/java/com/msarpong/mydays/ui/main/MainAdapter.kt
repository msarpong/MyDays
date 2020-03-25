package com.msarpong.mydays.ui.main

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.vipulasri.timelineview.TimelineView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.detail.DetailScreen
import com.msarpong.mydays.utils.formatDateTime
import kotlinx.android.synthetic.main.bottom_sheet.view.*
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

        holder.mTimelineView.lineStyle
        holder.diaryTitle.text = diary.title
        holder.diaryDate.text = diary.datetime.formatDateTime("yyyy-MM-dd HH:mm", "H:mm")
        holder.diaryCard.setOnClickListener {
            DetailScreen.openDetail(holder.diaryTitle.context as Activity, diary.id)
        }

    }
}

class NotesViewHolder(view: View, viewType: Int) : RecyclerView.ViewHolder(view) {
    var mTimelineView: TimelineView = itemView.findViewById<View>(R.id.timeline) as TimelineView

    val diaryTitle: TextView = view.findViewById(R.id.recycler_title)
    val diaryDate: TextView = view.findViewById(R.id.recycler_date)
    val diaryCard: CardView = view.findViewById(R.id.recycler_card)

    init {
        mTimelineView.initLine(viewType)
    }
}

class NotesDiffUtil : DiffUtil.ItemCallback<Notes>() {
    override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
        return oldItem == newItem
    }
}