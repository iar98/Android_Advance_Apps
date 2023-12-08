package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.recyclerViewAdapter.Adapter1
import com.example.myapplication.databinding.ActivityRecyclerViewBinding
import com.example.myapplication.recyclerViewModels.Model1

class RecyclerViewActivity : AppCompatActivity() {
    // binding xml
    private lateinit var binding: ActivityRecyclerViewBinding
    // binding recylerView
    private lateinit var adapter1: Adapter1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
        dropDown()
    }

    private fun dropDown() {
        binding.ivArrow.setOnClickListener {
            binding.ivArrow.setImageResource(R.drawable.arrow_clicked)
            binding.flDropDown.visibility = View.VISIBLE
            binding.flMath.visibility = View.GONE
        }

        binding.container.setOnClickListener {
            binding.ivArrow.setImageResource(R.drawable.arrow)
            binding.flDropDown.visibility = View.GONE
        }

        binding.textView2.setOnClickListener {
            binding.textView.text = "LinearLayout"
            binding.ivArrow.setImageResource(R.drawable.arrow)
            binding.flDropDown.visibility = View.GONE
            binding.flMath.visibility = View.GONE
            binding.rv1.layoutManager = LinearLayoutManager(this)
        }

        binding.textView3.setOnClickListener {
            var value = binding.tvNilai.text.toString().toInt()
            binding.textView.text = "GridLayout"
            binding.ivArrow.setImageResource(R.drawable.arrow)
            binding.flDropDown.visibility = View.GONE
            binding.flMath.visibility = View.VISIBLE
            binding.rv1.layoutManager = GridLayoutManager(this, value)
        }

        binding.btKurang.setOnClickListener {
            var value = binding.tvNilai.text.toString().toInt()
            if (value > 1) {
                value--
                binding.tvNilai.text = value.toString()
            }
            binding.rv1.layoutManager = GridLayoutManager(this, value)
        }

        binding.btTambah.setOnClickListener {
            var value = binding.tvNilai.text.toString().toInt()
            if (value < 5) {
                value++
                binding.tvNilai.text = value.toString()
            }
            binding.rv1.layoutManager = GridLayoutManager(this, value)
        }

        binding.btKembali.setOnClickListener {
            val intent = Intent(this@RecyclerViewActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setAdapter() {
        val dataList: MutableList<Model1> = mutableListOf()

        text().forEachIndexed {index, name ->
            dataList.add(Model1(name))
        }

        adapter1 = Adapter1(dataList)
        binding.rv1.adapter = adapter1
    }

    private fun text(): Array<String> = resources.getStringArray(R.array.RecyclerView)
}