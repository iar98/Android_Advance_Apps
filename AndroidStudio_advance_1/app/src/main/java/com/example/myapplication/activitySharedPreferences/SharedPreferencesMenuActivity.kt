package com.example.myapplication.activitySharedPreferences

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.SharedPreferenceActivity
import com.example.myapplication.databinding.ActivitySharedPreferencesMenuBinding

class SharedPreferencesMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySharedPreferencesMenuBinding
    private lateinit var prefs: CustumeSharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedPreferencesMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        checkLogin()
        logoutClick()
    }

    private fun init() {
        prefs = CustumeSharedPrefs(this@SharedPreferencesMenuActivity)
    }

    private fun checkLogin() {
        if(prefs.getLogin().equals(0)) {
            val intent = Intent(this@SharedPreferencesMenuActivity, SharedPreferenceActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // logika logout
    private fun logoutClick() {
        binding.btnLogout.setOnClickListener{
            prefs.saveLogin(0).let{
                val intent = Intent(this@SharedPreferencesMenuActivity, SharedPreferenceActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}