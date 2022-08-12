package com.shrouk.pato

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {
    lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)
        initView()

    }


    private fun initView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        controller = navHostFragment.navController
        controller.navigate(R.id.loginFragment)
    }




 //   override fun onSupportNavigateUp(): Boolean {
 //       val navcontroler = this.findNavController(R.id.myNavHostFragment)
 //       return navcontroler.navigateUp()
    }
//    val navcontroler = this.findNavController(R.id.myNavHostFragment)
//    NavigationUI.setupActionBarWithNavController(this, navcontroler)
//}
