package ort.edu.ar.futboltinder.activities.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ort.edu.ar.futboltinder.R
import ort.edu.ar.futboltinder.activities.home.HomeActivity

class MatchBaseFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_match_base, container, false)
    }

}