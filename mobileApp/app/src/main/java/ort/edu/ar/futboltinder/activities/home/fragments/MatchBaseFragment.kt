package ort.edu.ar.futboltinder.activities.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ort.edu.ar.futboltinder.R

class MatchBaseFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_base, container, false)
    }

    /*companion object {
        const val PARAM_NAME = "userId"

        fun newInstance(userId: Long?): MatchBaseFragment {
            val fragment = MatchBaseFragment()

            val bundle = Bundle().apply {
                putLong(PARAM_NAME, userId!!)
            }

            fragment.arguments = bundle

            return fragment
        }
    }*/

}