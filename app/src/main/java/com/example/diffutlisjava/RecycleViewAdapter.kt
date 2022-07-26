package com.example.diffutlisjava

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diffutlisjava.databinding.CardviewItemLayoutBinding

class RecycleViewAdapter : RecyclerView.Adapter<ViewHolder>() {
    private var listItem = arrayListOf<Model>()
    fun setData(data: List<Model>) {
        val diffResult = DiffUtil.calculateDiff(MyDiffUtilCallBack(data, listItem))
        diffResult.dispatchUpdatesTo(this)
        listItem.clear()
        listItem.addAll(data)
        //notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CardviewItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(listItem[position])
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val o = payloads[0] as Bundle
            for (key in o.keySet()) {
                if (key == "price") {
                    holder.changeItem(listItem[position])
                }
            }
        }
    }

    override fun getItemCount(): Int = listItem.size
}

class ViewHolder(private var binding: CardviewItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(itemModel: Model) {
        binding.txtName.text = itemModel.name
        binding.txtPrice.text = itemModel.price.toString()
    }

    fun changeItem(itemModel: Model) {
        binding.txtName.text = itemModel.name
        binding.txtPrice.text = itemModel.price.toString()
        binding.txtPrice.setTextColor(Color.GREEN)
    }
}