package ort.edu.ar.futboltinder.listener

import ort.edu.ar.futboltinder.model.Match

interface OnMatchClickedListener {
    fun onMatchSelected(match : Match)
}