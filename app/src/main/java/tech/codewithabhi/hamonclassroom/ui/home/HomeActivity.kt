package tech.codewithabhi.hamonclassroom.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_home.*
import tech.codewithabhi.hamonclassroom.R

class HomeActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        navController = Navigation.findNavController(this, R.id.fragment_home_nav)
        NavigationUI.setupWithNavController(bottom_navigation, navController)
    }
}
