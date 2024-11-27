package com.example.messenger.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.messenger.R
import com.example.messenger.databinding.ActivityMainBinding
import com.example.messenger.ui.main.auth.NODE_USERS
import com.example.messenger.ui.main.auth.REF_DATABASE_ROOT
import com.example.messenger.ui.main.auth.UID
import com.example.messenger.ui.main.auth.USER
import com.example.messenger.ui.main.auth.initFirebase
import com.example.messenger.ui.main.models.User
import com.example.messenger.ui.main.utils.AppValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        val builder = AppBarConfiguration.Builder(navController.graph)

        builder.setOpenableLayout(binding.drawerLayout)

        val appBarConfiguration = builder.build()
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        NavigationUI.setupWithNavController(binding.navView, navController)

        initFirebase()
        initUser()

    }

    private fun initUser(){
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID).addListenerForSingleValueEvent(
            AppValueEventListener{
                USER = it.getValue(User::class.java) ?: User()
            }
        )
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}