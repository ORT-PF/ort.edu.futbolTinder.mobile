package ort.edu.ar.futboltinder.activities.helpers.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ort.edu.ar.futboltinder.R
import ort.edu.ar.futboltinder.domain.Match.Forms.MatchCreatorForm
import ort.edu.ar.futboltinder.domain.Match.Responses.MatchListResponse
import ort.edu.ar.futboltinder.domain.Match.viewModels.Match

class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val titleText:         TextView = itemView.findViewById(R.id.textViewName)
    private val titleFieldAddress: TextView = itemView.findViewById(R.id.textViewFieldAddress)
    private val titlePlayers:      TextView = itemView.findViewById(R.id.textViewPlayers)

    fun bind(match : MatchListResponse) {

        titleText.text    = match.fieldName
        titleFieldAddress.text = match.fieldAddress
        titlePlayers.text = match.originalQuota
    }
}