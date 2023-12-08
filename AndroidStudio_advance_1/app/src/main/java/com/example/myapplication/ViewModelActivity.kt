package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.databinding.ActivityViewModelBinding
import com.example.myapplication.viewModel.ViewModelActivityViewModel

class ViewModelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewModelBinding

    private lateinit var vm: ViewModelActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewModelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        action()
        // MVVM
        init()
        changeText()
        // Mengamati perubahan pada currentIndex dan mengupdate tv_nilai
        vm.currentIndex.observe(this, Observer { newValue ->
            binding.tvNilai.text = newValue.toString()
        })
    }

    private fun init() {
        vm = ViewModelProvider(this).get(ViewModelActivityViewModel::class.java)
    }

    private fun changeText() {
        binding.btTextView.setOnClickListener {
            if(vm.indexNumber2.equals(0)) {
                vm.setNumber2(++vm.indexNumber2)
            }else{
                vm.setNumber2(--vm.indexNumber2)
            }
        }

        setChangeText()
    }

    private fun setChangeText() {
        var lisText = listOf(
            "View Model",
            "Ledom Weiv"
        )
        val observer2 = Observer<Int>{index ->
            binding.textView.text = lisText[index]
        }

        vm.getNumber2().observe(this, observer2)
    }

    private fun action() {
        binding.btKurang.setOnClickListener {
            var value = binding.tvNilai.text.toString().toInt()
            if (value > 1) {
                value--
                binding.tvNilai.text = value.toString()
                // Memperbarui nilai di ViewModel
                vm.setNumber(value)
            }
        }

        binding.btTambah.setOnClickListener {
            var value = binding.tvNilai.text.toString().toInt()
            if (value < 5) {
                value++
                binding.tvNilai.text = value.toString()
                // Memperbarui nilai di ViewModel
                vm.setNumber(value)
            }
        }
    }
}