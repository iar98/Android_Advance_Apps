package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityAppBarBinding
import com.example.myapplication.databinding.CustomToolbarBinding // Tambahkan import ini

class AppBarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppBarBinding
    private lateinit var customToolbarBinding: CustomToolbarBinding // Tambahkan ini

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppBarBinding.inflate(layoutInflater)
        customToolbarBinding = binding.customToolBar // Inisialisasi binding untuk custom toolbar
        setContentView(binding.root)

        action()
    }

    private fun action() {
        customToolbarBinding.ivBack.setOnClickListener {
            showToast("Back button di pencet")
        }

        customToolbarBinding.ivNotifications.setOnClickListener {
            showToast("notifikasi di pencet")
        }

        customToolbarBinding.ivSetting.setOnClickListener {
            showToast("setting di pencet")
        }

        customToolbarBinding.tvTitle.text = "Custom Toolbar"

        binding.btKembali.setOnClickListener {
            val intent = Intent(this@AppBarActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showToast(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.show()

        // Menampilkan Toast selama 0,3 detik
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({ toast.cancel() }, 800)
    }
}
