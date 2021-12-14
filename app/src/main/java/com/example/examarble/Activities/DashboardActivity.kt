package com.example.examarble.Activities

import com.example.examarble.R
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.examarble.IBadgeUpdater
import com.example.examarble.INavListener
import com.example.examarble.databinding.ActivityDashboardBinding
import com.example.examarble.utils.SharedPreferenceManager
import com.example.examarble.viewModel.StoreViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashboardActivity : AppCompatActivity(),INavListener, IBadgeUpdater {
    lateinit var sharedPreferenceManager: SharedPreferenceManager
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDashboardBinding
    lateinit var bottomNavView: BottomNavigationView
    private val storeViewModel: StoreViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)
        sharedPreferenceManager = SharedPreferenceManager(this)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        bottomNavView = findViewById(R.id.nav_view1)
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.home, R.id.profile, R.id.cart
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        bottomNavView.setupWithNavController(navController)
        updateBadge()

        val headerView: View = navView.getHeaderView(0)
        val navUsername = headerView.findViewById<View>(R.id.userName) as TextView
        val navUserEmail = headerView.findViewById<View>(R.id.UserEmail) as TextView
        val navUserMobile = headerView.findViewById<View>(R.id.UserMobile) as TextView
        val userData:Array<String> = sharedPreferenceManager.getUserData()
        navUsername.text = userData[0]
        navUserEmail.text = ""
        navUserMobile.text = userData[1]
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun showHideNavigations(flag: Boolean) {
//        val bottomNavViewnavView: NavigationView = binding.navView
//        val bottomNavView: BottomNavigationView = findViewById(R.id.nav_view1)
//        if (flag){
//            bottomNavView.visibility = View.VISIBLE
//            //binding.navView1.visibility = View.VISIBLE
//        }else{
//            bottomNavView.visibility = View.GONE
//            //bottomNav.visibility = View.GONE
//        }
    }

    override fun updateBadge() {
//        storeViewModel.getCartItems()
//        GlobalScope.launch(Dispatchers.Main) {
//            delay(800)
//            storeViewModel.cartList.observe(this@DashboardActivity, Observer { list->
//                Log.i("main Activity",list.size.toString())
//                if (list.size>0) {
//                    var badge = bottomNavView.getOrCreateBadge(R.id.cart)
//                    badge.isVisible = true
//                    badge.number = list.size
//                }else{
//                    bottomNavView.removeBadge(R.id.cart)
//                }
//            })
//        }

    }
//    override fun showHideNavigations(flag: Boolean) {
//        if (flag) {
//            //main_app_bar.visibility = View.VISIBLE
//            bottomNav.visibility = View.VISIBLE
//        } else {
//            //main_app_bar.visibility = View.GONE
//            bottomNav.visibility = View.GONE
//        }
//    }
}