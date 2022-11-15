package ort.edu.ar.futboltinder.activities.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import ort.edu.ar.futboltinder.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HomeActivity : AppCompatActivity() {
    private lateinit var bottomNavView : BottomNavigationView
    private lateinit var navHostFragment : NavHostFragment

    private var userId : Long? = null
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Busco y guardo la referencia a las vistas en variables
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment

        setupDrawerLayout()

        userId = intent.getLongExtra(getString(R.string.USER_ID_PARAM_NAME), 10000)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment

        setLoggedUserId(userId!!)

        bottomNavView = findViewById(R.id.bottomAppBar2)

        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)
    }

    private fun setupDrawerLayout() {

        val navController = navHostFragment.navController
        // Vinculo la navegación del drawer con la del graph
        navigationView.setupWithNavController(navController)

        // Configuro la appbar para que muestre el icono del drawer y actualice el titulo
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        // Listener para cuando se realiza la navegacion
        navController.addOnDestinationChangedListener { _, _, _ ->
            // Aca le digo que quiero que mi icono izquierdo de la appbar sea el del drawer
            supportActionBar?.setHomeAsUpIndicator(R.drawable.hamburger)

        }
    }
    // Forzar el drawer a que se abra siempre
    override fun onSupportNavigateUp(): Boolean {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        return false
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

        fun endSession(){
            loggedUserId = null
        }

        fun setUserCurrentLocation(location : LatLng){
            userCurrentLocation = location
        }

        fun getUserCurrentLocation() : LatLng?{
            return userCurrentLocation
        }

        fun formatDateTime(dateToFormat: String): String {

        val localDateTime: LocalDateTime = LocalDateTime.parse(dateToFormat)
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy   HH:mm")
        return formatter.format(localDateTime)
    }
    }
}