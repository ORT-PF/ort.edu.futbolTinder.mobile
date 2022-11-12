package ort.edu.ar.futboltinder.activities.helpers.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ort.edu.ar.futboltinder.R
import ort.edu.ar.futboltinder.activities.home.HomeActivity.Companion.formatDateTime
import ort.edu.ar.futboltinder.domain.Match.Forms.MatchCreatorForm
import ort.edu.ar.futboltinder.domain.Match.Responses.MatchListResponse
import ort.edu.ar.futboltinder.domain.Match.viewModels.Match
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val titleText:         TextView = itemView.findViewById(R.id.textViewName)
    private val titleFieldAddress: TextView = itemView.findViewById(R.id.textViewFieldAddress)
    //private val titlePlayers:      TextView = itemView.findViewById(R.id.textViewPlayers)
    private val titleMatchDateTime:     TextView = itemView.findViewById(R.id.textViewDateTime)

    fun bind(match : MatchListResponse) {

        titleText.text    = match.fieldName
        titleFieldAddress.text = match.fieldAddress
        //titlePlayers.text = match.originalQuota.toString()
        titleMatchDateTime.text = formatDateTime(match.dateTime)
    }
}