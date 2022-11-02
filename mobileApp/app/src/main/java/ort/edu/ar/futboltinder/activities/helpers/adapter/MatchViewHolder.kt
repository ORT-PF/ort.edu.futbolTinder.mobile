package ort.edu.ar.futboltinder.activities.helpers.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ort.edu.ar.futboltinder.R
import ort.edu.ar.futboltinder.domain.Match.Forms.MatchCreatorForm
import ort.edu.ar.futboltinder.domain.Match.Responses.MatchListResponse
import ort.edu.ar.futboltinder.domain.Match.viewModels.Match

class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val titleText: TextView = itemView.findViewById(R.id.item_title)

    fun bind(match : MatchListResponse) {

        titleText.text = match.fieldName

    }
}