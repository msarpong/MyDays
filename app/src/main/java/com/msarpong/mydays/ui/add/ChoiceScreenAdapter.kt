package com.msarpong.mydays.ui.add

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.add.sport.AddSportScreen
import com.msarpong.mydays.ui.add.text.AddTextScreen

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
                    "Sport" -> AddSportScreen.OpenAddSport(holder.card.context as Activity)
                    "Text" -> AddTextScreen.OpenAddText(holder.card.context as Activity)
                }
            }
        }
    }

}

class ChoiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val cardLabel: TextView = itemView.findViewById(R.id.card_label)
    val cardIcon: ImageView = itemView.findViewById(R.id.card_icon)
    val card: CardView = itemView.findViewById(R.id.card_item)
}

class ChoiceDiffCallback : DiffUtil.ItemCallback<Choice>() {
    override fun areItemsTheSame(oldItem: Choice, newItem: Choice): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Choice, newItem: Choice): Boolean {
        return oldItem == newItem
    }
}