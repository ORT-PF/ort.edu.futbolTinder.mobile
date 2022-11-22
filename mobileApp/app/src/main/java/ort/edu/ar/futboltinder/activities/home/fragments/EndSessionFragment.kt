package ort.edu.ar.futboltinder.activities.home.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import ort.edu.ar.futboltinder.R
import ort.edu.ar.futboltinder.activities.home.HomeActivity
import ort.edu.ar.futboltinder.activities.login.LoginActivity

class EndSessionFragment : Fragment() {
    private lateinit var vista : View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.title =
            "Cerrar Sesión"
        vista = inflater.inflate(R.layout.fragment_end_session, container, false)
        return vista
    }

    override fun onStart() {
        super.onStart()

        Handler(Looper.getMainLooper()).postDelayed({
            navigateLoginActivity()
        }, 1000)
    }

    private fun navigateLoginActivity() {
        HomeActivity.endSession()
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
    }
}