package ort.edu.ar.futboltinder.activities.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
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

        bottomNavView = findViewById(R.id.bottomAppBar2)

        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)
    }

    companion object{
        private var loggedUserId : Long? = null

        fun setLoggedUserId(loggedUserIdParam : Long){
            if(loggedUserId == null){
                loggedUserId = loggedUserIdParam
            }
        }

        fun getCurrentUserId() : Long?{
            return loggedUserId
        }
    }
}