package tech.codewithabhi.hamonclassroom.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import tech.codewithabhi.hamonclassroom.R

class AuthMainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_main)
        navController = Navigation.findNavController(this, R.id.fragment_auth)
    }
}
