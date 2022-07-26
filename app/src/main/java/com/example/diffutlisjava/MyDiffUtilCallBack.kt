package com.example.diffutlisjava

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DiffUtil

class MyDiffUtilCallBack(
    private var newList: List<Model>,
    private var oldList: List<Model>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition].id == oldList[oldItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition] == oldList[oldItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        Log.d("iamquan1705", "payload old: $oldItemPosition - new : $newItemPosition")
        val newModel: Model = newList[newItemPosition]
        val oldModel: Model = oldList[oldItemPosition]
        val diff = Bundle()
        if (newModel.price != oldModel.price) {
            diff.putInt("price", newModel.price)
        }
        return if (diff.size() == 0) {
            null
        } else diff

    }
}