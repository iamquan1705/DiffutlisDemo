package com.example.diffutlisjava

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diffutlisjava.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var recyclerViewAdapter: RecycleViewAdapter? = null
    private lateinit var viewModel: DemoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(DemoViewModel::class.java)
        viewModel.setList()
        recyclerViewAdapter = RecycleViewAdapter()
        setUpRC()
        initObserver()
        initOnClick()
    }

    private fun initObserver() {
        viewModel.listStudent.observe(this) {
            recyclerViewAdapter?.setData(it)
        }
    }

    private fun initOnClick() {
        binding.apply {
            fabAddList.setOnClickListener {
                viewModel.add()
            }
            fabChangeList.setOnClickListener {
                viewModel.changePricesInTheList()
            }
        }
    }

    private fun setUpRC() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            itemAnimator = DefaultItemAnimator()
            adapter = recyclerViewAdapter
        }
    }
}