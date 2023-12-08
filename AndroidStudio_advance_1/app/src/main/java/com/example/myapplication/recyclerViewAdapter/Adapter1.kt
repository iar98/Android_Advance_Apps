package com.example.myapplication.recyclerViewAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemrecyclerviewBinding
import com.example.myapplication.recyclerViewModels.Model1

class Adapter1(private val list: List<Model1>): RecyclerView.Adapter<Adapter1.ViewHolder>() {

    class ViewHolder(val binding: ItemrecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemrecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(list[position]) {
                binding.tvText.text = this.text
            }
        }
    }

}