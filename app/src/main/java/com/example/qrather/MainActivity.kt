package com.example.qrather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import fragments.GenerateFragment
import fragments.HelpFragment
import fragments.HomeFragment
import fragments.ProfileFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val generateFragment = GenerateFragment()
        val helpFragment = HelpFragment()
        val profileFragment =ProfileFragment()

        makeCurrentFragment(homeFragment)


        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> makeCurrentFragment(homeFragment)
                R.id.ic_generate -> makeCurrentFragment(generateFragment)
                R.id.ic_help -> makeCurrentFragment(helpFragment)
                R.id.ic_profile -> makeCurrentFragment(profileFragment)
            }
            true
        }


    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }
}