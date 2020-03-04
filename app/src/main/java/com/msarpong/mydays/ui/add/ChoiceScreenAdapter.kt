package com.msarpong.mydays.ui.add

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.msarpong.mydays.R

class ChoiceScreenAdapter : ListAdapter<Choice, ChoiceViewHolder>(ChoiceDiffCallback()) {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return ChoiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChoiceViewHolder, position: Int) {
        val cardItem = getItem(position)

        cardItem.let {
            holder.cardLabel.text = it.title
            holder.cardIcon.setImageResource(it.image)
            holder.card.setOnClickListener {
                when (holder.cardLabel.text) {
                    "sport" -> ""
                }
            }
        }
    }

}

class ChoiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val cardLabel = itemView.findViewById<TextView>(R.id.card_label)
    val cardIcon = itemView.findViewById<ImageView>(R.id.card_icon)
    val card = itemView.findViewById<CardView>(R.id.card_item)
}

class ChoiceDiffCallback : DiffUtil.ItemCallback<Choice>() {
    override fun areItemsTheSame(oldItem: Choice, newItem: Choice): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Choice, newItem: Choice): Boolean {
        return oldItem == newItem
    }
}