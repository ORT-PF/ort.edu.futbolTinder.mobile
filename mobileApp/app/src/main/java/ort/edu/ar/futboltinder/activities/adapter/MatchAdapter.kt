package ort.edu.ar.futboltinder.activities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ort.edu.ar.futboltinder.R
import ort.edu.ar.futboltinder.activities.listener.OnMatchClickedListener
import ort.edu.ar.futboltinder.domain.Match.viewModels.Match

class MatchAdapter(
    private val matchList: List<Match>,
    private val onMatchClickedListener: OnMatchClickedListener
    ) : RecyclerView.Adapter<MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.match_item,parent, false)
        return MatchViewHolder(view)

    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = matchList[position]
        holder.bind(match)

        holder.itemView.setOnClickListener { onMatchClickedListener.onMatchSelected(match) }

    }

    override fun getItemCount(): Int {
        return matchList.size
    }


}