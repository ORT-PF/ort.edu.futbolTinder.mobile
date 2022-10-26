package ort.edu.ar.futboltinder.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ort.edu.ar.futboltinder.R
import ort.edu.ar.futboltinder.model.Match

class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val titleText: TextView = itemView.findViewById(R.id.item_title)

    fun bind(match : Match) {

        titleText.text = match.name

    }
}