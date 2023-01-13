package com.example.stickyheadertest.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stickyheadertest.databinding.ItemNumberBinding

/**
 * Created by PS Wang on 2023/1/12
 */
class NumberAdapter(private val numberList: List<NumberSectionData>) :
    RecyclerView.Adapter<NumberAdapter.NumberViewHolder>() {

    class NumberViewHolder(private val binding: ItemNumberBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(number: Int) {
            binding.root.text = number.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        return NumberViewHolder(ItemNumberBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind(numberList[position].number)
    }

    override fun getItemCount() = numberList.size
}