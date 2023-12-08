package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.activitySharedPreferences.CustumeSharedPrefs
import com.example.myapplication.activitySharedPreferences.SharedPreferencesMenuActivity
import com.example.myapplication.databinding.ActivitySharedPreferenceBinding

class SharedPreferenceActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySharedPreferenceBinding
    private lateinit var prefs: CustumeSharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        checkCondition()
        loginClick()
    }

    private fun init() {
        prefs = CustumeSharedPrefs(this@SharedPreferenceActivity)
    }

    private fun checkCondition() {
        if(!prefs.getLogin().equals(0)) {
            val intent = Intent(this@SharedPreferenceActivity, SharedPreferencesMenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loginClick() {
        binding.btnLogin.setOnClickListener {
            prefs.saveLogin(1).let {
                val intent = Intent(this@SharedPreferenceActivity, SharedPreferencesMenuActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}