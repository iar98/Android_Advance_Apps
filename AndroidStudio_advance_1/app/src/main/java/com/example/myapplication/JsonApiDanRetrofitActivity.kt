package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityJsonApiDanRetrofitBinding
import com.example.myapplication.jsonAndRetrofitRepository.Repository

class JsonApiDanRetrofitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJsonApiDanRetrofitBinding

    // viewModel
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJsonApiDanRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer { response ->
            if(response.isSuccessful) {
                binding.textView.text = response.body()?.userId.toString()
                binding.textView2.text = response.body()?.id.toString()
                binding.textView3.text = response.body()?.title!!
                binding.textView4.text = response.body()?.body!!
            }else {
                Log.d("Response", response.errorBody().toString())
                binding.textView.text = response.code().toString()
            }
        })
    }
}