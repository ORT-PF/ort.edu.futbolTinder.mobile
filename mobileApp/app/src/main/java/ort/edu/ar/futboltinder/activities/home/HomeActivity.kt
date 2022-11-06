package ort.edu.ar.futboltinder.activities.home

import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomnavigation.BottomNavigationView
import ort.edu.ar.futboltinder.R

class HomeActivity : AppCompatActivity() {
    private lateinit var bottomNavView : BottomNavigationView
    private lateinit var navHostFragment : NavHostFragment
    private var userId : Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        userId = intent.getLongExtra(getString(R.string.USER_ID_PARAM_NAME), 10000)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment

        HomeActivity.setLoggedUserId(userId!!)
        val loc = HomeActivity.getUserCurrentLocation()

        bottomNavView = findViewById(R.id.bottomAppBar2)

        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)
    }

    companion object{
        private var loggedUserId : Long? = null
        private var userCurrentLocation : LatLng? = null

        fun setLoggedUserId(loggedUserIdParam : Long){
            if(loggedUserId == null){
                loggedUserId = loggedUserIdParam
            }
        }

        fun getCurrentUserId() : Long?{
            return loggedUserId
        }

        fun setUserCurrentLocation(location : LatLng){
            userCurrentLocation = location
        }

        fun getUserCurrentLocation() : LatLng?{
            return userCurrentLocation
        }
    }
}