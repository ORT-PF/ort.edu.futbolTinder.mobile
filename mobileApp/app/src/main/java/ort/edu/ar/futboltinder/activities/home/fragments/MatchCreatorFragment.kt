package ort.edu.ar.futboltinder.activities.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import ort.edu.ar.futboltinder.R
import ort.edu.ar.futboltinder.domain.Match.Forms.MatchCreatorForm


class MatchCreatorFragment : Fragment() {
    lateinit var vista: View
    lateinit var matchCreateButton: Button
    lateinit var nameText: EditText
    lateinit var quotaNumber: EditText
    //lateinit var dateText: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_match_creator, container, false)
        nameText = vista.findViewById(R.id.nameMatchCreatorForm)
        quotaNumber = vista.findViewById(R.id.quotaMatchCreatorForm)
        matchCreateButton = vista.findViewById(R.id.buttonMatchCreatorForm)

        return vista
    }

    override fun onStart() {
        super.onStart()

        matchCreateButton.setOnClickListener {
            var isValidContext = validateContext()

            if (isValidContext) {
                var matchCreatorForm = MatchCreatorForm(
                    nameText.text.toString(),
                    quotaNumber.text.toString().toInt(),
                    null,
                    null,
                    null
                )
                //matchCreatorService.register(matchCreatorForm)
                /*val intent = Intent(activity, MapsActivity::class.java)
                intent.putExtra("match", matchCreatorForm)
                startActivity(intent)*/
                val action =
                    MatchCreatorFragmentDirections.actionMatchCreatorFragmentToMapsFragment(matchCreatorForm)
                vista.findNavController().navigate(action)
            }
        }
    }
        private fun validateContext(): Boolean {
            if (nameText.text.isNullOrEmpty()) {
                Toast.makeText(activity, "El nombre de la cancha es requerido", Toast.LENGTH_LONG).show()
                return false
            }
            if (quotaNumber.text.isNullOrEmpty()) {
                Toast.makeText(activity, "El cupo de jugadores es requerido", Toast.LENGTH_LONG).show()
                return false
            }
            return true
        }
    }
