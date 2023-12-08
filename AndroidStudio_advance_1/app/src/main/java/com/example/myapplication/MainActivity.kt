package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.room.RoomActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dropdown()
        gasBuka()
    }

    private fun dropdown() {
        binding.ivArrow.setOnClickListener {
            binding.ivArrow.setImageResource(R.drawable.arrow_clicked)
            binding.btGas.visibility = View.GONE
            binding.svTugas.visibility = View.VISIBLE
        }

        binding.container.setOnClickListener {
            binding.svTugas.visibility = View.GONE
            binding.btGas.visibility = View.VISIBLE
            binding.ivArrow.setImageResource(R.drawable.arrow)
        }

        binding.tvDropdown.setOnClickListener {
            binding.ivArrow.setImageResource(R.drawable.arrow_clicked)
            binding.btGas.visibility = View.GONE
            binding.svTugas.visibility = View.VISIBLE
        }

        binding.tvRecyclerView.setOnClickListener {
            action1("RecyclerView")
        }

        binding.tvAppBar.setOnClickListener {
            action1("App bar / ActionBar / ToolBar")
        }

        binding.tvNavigationDrawer.setOnClickListener {
            action1("Navigation Drawer")
        }

        binding.tvBottomNavigation.setOnClickListener {
            action1("Bottom Navigation")
        }

        binding.tvTabLayout.setOnClickListener {
            action1("Tab Layout")
        }

        binding.tvJsonApiDanRetrofit.setOnClickListener {
            action1("Json API dan Retrofit")
        }

        binding.tvViewModel.setOnClickListener {
            action1("View Model")
        }

        binding.tvSharedPreference.setOnClickListener {
            action1("SharedPreference")
        }

        binding.tvAnimation.setOnClickListener {
            action1("Animation")
        }

        binding.tvBrainTrainer.setOnClickListener {
            action1("Brain Trainer Apps")
        }

        binding.tvRoom.setOnClickListener {
            action1("Room")
        }

        binding.tvAlaramManager.setOnClickListener {
            action1("Alaram Manager")
        }

        binding.tvWorkManager.setOnClickListener {
            action1("Work Manager")
        }
    }

    private fun action1(parameter: String) {
        binding.tvDropdown.setText(parameter)
        binding.svTugas.visibility = View.GONE
        binding.btGas.visibility = View.VISIBLE
        binding.ivArrow.setImageResource(R.drawable.arrow)
    }

    private fun gasBuka() {
        binding.btGas.setOnClickListener {
            val dropDown = binding.tvDropdown.text.toString()
            when(dropDown) {
                "RecyclerView" -> {
                    val intent = Intent(this@MainActivity, RecyclerViewActivity::class.java)
                    startActivity(intent)
                }
                "App bar / ActionBar / ToolBar" -> {
                    val intent = Intent(this@MainActivity, AppBarActivity::class.java)
                    startActivity(intent)
                }
                "Navigation Drawer" -> {
                    val intent = Intent(this@MainActivity, NavigationDrawerActivity::class.java)
                    startActivity(intent)
                }
                "Bottom Navigation" -> {
                    val intent = Intent(this@MainActivity, BottomNavigationActivity::class.java)
                    startActivity(intent)
                }
                "Tab Layout" -> {
                    val intent = Intent(this@MainActivity, TabLayoutActivity::class.java)
                    startActivity(intent)
                }
                "Json API dan Retrofit" -> {
                    val intent = Intent(this@MainActivity, JsonApiDanRetrofitActivity::class.java)
                    startActivity(intent)
                }
                "View Model" -> {
                    val intent = Intent(this@MainActivity, ViewModelActivity::class.java)
                    startActivity(intent)
                }
                "SharedPreference" -> {
                    val intent = Intent(this@MainActivity, SharedPreferenceActivity::class.java)
                    startActivity(intent)
                }
                "Animation" -> {
                    val intent = Intent(this@MainActivity, AnimationActivity::class.java)
                    startActivity(intent)
                }
                "Brain Trainer Apps" -> {
                    val intent = Intent(this@MainActivity, BrainTrainerAppsActivity::class.java)
                    startActivity(intent)
                }
                "Room" -> {
                    val intent = Intent(this@MainActivity, RoomActivity::class.java)
                    startActivity(intent)
                }
                "Alaram Manager" -> {
                    val intent = Intent(this@MainActivity, AlaramManagerActivity::class.java)
                    startActivity(intent)
                }
                "Work Manager" -> {
                    val intent = Intent(this@MainActivity, WorkManagerActivity::class.java)
                    startActivity(intent)
                }

            }
        }
    }
}