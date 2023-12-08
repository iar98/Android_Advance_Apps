package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myapplication.bottomNavbarFragment.HomesFragment
import com.example.myapplication.bottomNavbarFragment.LogoutFragment
import com.example.myapplication.bottomNavbarFragment.MessagesFragment
import com.example.myapplication.bottomNavbarFragment.SettingssFragment
import com.example.myapplication.databinding.ActivityBottomNavigationBinding

class BottomNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFragment(HomesFragment())

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> setFragment(HomesFragment())
                R.id.setting -> setFragment(SettingssFragment())
                R.id.message -> setFragment(MessagesFragment())
                R.id.logout -> setFragment(LogoutFragment())
            }

            return@setOnItemSelectedListener true
        }
    }

    private fun setFragment(fragment: Fragment) {
        val transaction =supportFragmentManager.beginTransaction()
        // menukar fragment
        transaction.replace(R.id.fragmentFl, fragment)
        // untuk tidak menumpuk fragment
        transaction.disallowAddToBackStack()
        // menyatakan transaction di jalankan
        transaction.commit()
    }
}