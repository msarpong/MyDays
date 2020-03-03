package com.msarpong.mydays.ui.add

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.msarpong.mydays.R
import kotlinx.android.synthetic.main.card_item.view.*


class ChoiceScreenAdapter : ListAdapter<Choice, ChoiceViewHolder>(ChoiceDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return ChoiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChoiceViewHolder, position: Int) {
        val cardItem = getItem(position)
        cardItem.let {
            holder.cardLabel.text = it.title
            holder.cardIcon.setImageResource(it.image)
        }
    }

}

class ChoiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val cardLabel = itemView.findViewById<TextView>(R.id.card_label)
    val cardIcon = itemView.findViewById<ImageView>(R.id.card_icon)
}

class ChoiceDiffCallback : DiffUtil.ItemCallback<Choice>() {
    override fun areItemsTheSame(oldItem: Choice, newItem: Choice): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Choice, newItem: Choice): Boolean {
        return oldItem == newItem
    }
}