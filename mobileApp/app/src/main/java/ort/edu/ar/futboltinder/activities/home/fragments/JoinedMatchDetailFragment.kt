package ort.edu.ar.futboltinder.activities.home.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import ort.edu.ar.futboltinder.R
import ort.edu.ar.futboltinder.activities.home.HomeActivity
import ort.edu.ar.futboltinder.activities.home.HomeActivity.Companion.formatDateTime
import ort.edu.ar.futboltinder.services.APIServices.RetrofitClientBuilderHeroku
import ort.edu.ar.futboltinder.services.APIServices.RetrofitContracts.JoinMatch.RetrofitMatchPlayerService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class JoinedMatchDetailFragment : Fragment() {
    private var userId = HomeActivity.getCurrentUserId()!!.toInt()
    private lateinit var vista : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.title =
            "Detalle del Partido"
        vista = inflater.inflate(R.layout.fragment_joined_match_detail, container, false)
        // Inflate the layout for this fragment
        return vista
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val match = MatchDetailFragmentArgs.fromBundle(requireArguments()).match
        val matchName = vista.findViewById<TextView>(R.id.textViewName)
        matchName.text =
            "Nombre: " + match.fieldName

        val matchAdress = vista.findViewById<TextView>(R.id.textViewAdress)
        matchAdress.text =
            "Direccion: " + match.fieldAddress

        val matchQuotaNumber = vista.findViewById<TextView>(R.id.textViewQuotaNumber)

         val cuposLibres    = match.remainingQuota

        matchQuotaNumber.text = "Cupos libres: $cuposLibres"

        val matchDateTimeDetail = vista.findViewById<TextView>(R.id.textViewDateTimeDetail)
        matchDateTimeDetail.text ="Fecha: " + formatDateTime(match.dateTime)

        val buttonLeaveMatch = vista.findViewById<Button>(R.id.buttonLeaveMatch)

        buttonLeaveMatch.setOnClickListener {
                leaveMatch(userId, match.id)
        }
    }

    private fun leaveMatch(userId: Int, matchId: Int) {
        val retrofitClient = RetrofitClientBuilderHeroku.buildService(
            RetrofitMatchPlayerService::class.java)

        retrofitClient.leaveMatch(userId, matchId).enqueue(object :
            Callback<Void> {
            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                if (response.isSuccessful) {
                    val action = JoinedMatchDetailFragmentDirections.actionJoinedMatchDetailFragmentToJoinedMatchFragment()
                    vista.findNavController().navigate((action))
                }
                else{
                    Log.e("APIERROR", response.message())
                    Toast.makeText(context, getString(R.string.api_error), Toast.LENGTH_LONG)
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(context, getString(R.string.api_error), Toast.LENGTH_LONG)
            }
        })
    }


}