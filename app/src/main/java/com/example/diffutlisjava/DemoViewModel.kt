package com.example.diffutlisjava

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class DemoViewModel : ViewModel() {
    var listStudent = MutableLiveData<List<Model>>()
    private var list = arrayListOf<Model>(
        Model(1, "Bitcoin", 8000),
        Model(2, "Ethereum", 600),
        Model(3, "Litecoin", 250),
        Model(4, "Bitcoin Cash", 100),
        Model(5, "Bitcoin Cash", 100),
    )

    fun setList() {
        listStudent.value = list
    }

    fun changePricesInTheList() {
        val listChange = arrayListOf<Model>()
        list.forEach {
            listChange.add(Model(it.id, it.name, 100))
        }
        listStudent.value = listChange
    }

    fun add() {
        for (i in 5..7) {
            list.add(Model(id = i, name = " Quan ", price = Random().nextInt(1000)))
        }
        listStudent.value = list
    }
}