package com.example.lokal_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lokal_assignment.adapter.CurrencyAdapter
import com.example.lokal_assignment.databinding.ActivityMainBinding
import com.example.lokal_assignment.utils.Resource
import com.example.lokal_assignment.viewModels.HomeViewFactory
import com.example.lokal_assignment.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var CurrencyAdapter: CurrencyAdapter

    @Inject
    lateinit var homeViewFactory: HomeViewFactory
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        homeViewModel = ViewModelProvider(this, homeViewFactory)[HomeViewModel::class.java]
        homeViewModel.cryptoCurrencyList.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    binding?.rcvCryptoCurrencyList?.visibility = View.VISIBLE
                    if (binding?.swipeRefreshLayout?.isRefreshing == true) {
                        binding?.swipeRefreshLayout?.isRefreshing = false
                    }
                    binding?.rcvCryptoCurrencyList?.visibility = View.VISIBLE
                    CurrencyAdapter =
                        response.data?.let {
                            CurrencyAdapter(this@MainActivity, it) { cryptoCurrency ->
                                Toast.makeText(
                                    this,
                                    cryptoCurrency.name + " selected.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }!!
                    binding?.rcvCryptoCurrencyList?.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = CurrencyAdapter
                    }
                }

                is Resource.Loading -> {
                    binding?.rcvCryptoCurrencyList?.visibility = View.INVISIBLE
                }

                is Resource.Error -> {
                    binding?.rcvCryptoCurrencyList?.visibility = View.VISIBLE
                    if (binding?.swipeRefreshLayout?.isRefreshing == true) {
                        binding?.swipeRefreshLayout?.isRefreshing = false
                    }
                    Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                }
            }
        })


        binding?.swipeRefreshLayout?.setOnRefreshListener {
            homeViewModel.reloadCryptoCurrency()
        }
        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.DOWN) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                binding?.swipeRefreshLayout?.isRefreshing = true
                binding?.swipeRefreshLayout?.setOnRefreshListener(null)
            }
        }
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding?.rcvCryptoCurrencyList)
    }


}