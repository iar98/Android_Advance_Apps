package com.example.myapplication.bottomNavbarFragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomesBinding
import com.example.myapplication.databinding.FragmentSettingssBinding

class SettingssFragment : Fragment() {

    private lateinit var binding: FragmentSettingssBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSettingssBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.kembali.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}