package ort.edu.ar.futboltinder.activities.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import ort.edu.ar.futboltinder.R
import ort.edu.ar.futboltinder.activities.home.fragments.MatchBaseFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var bottomNavView : BottomNavigationView
    private lateinit var navHostFragment : NavHostFragment
    private var userId : Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        userId = intent.getLongExtra("userId", 10000)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment

        bottomNavView = findViewById(R.id.bottomAppBar2)

        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)
    }

    fun getUserId() : Long?{
        return userId
    }
}