package ort.edu.ar.futboltinder.activities.listener

import ort.edu.ar.futboltinder.domain.Match.viewModels.Match

interface OnMatchClickedListener {
    fun onMatchSelected(match : Match)
}