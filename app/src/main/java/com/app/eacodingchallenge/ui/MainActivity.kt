package com.app.eacodingchallenge.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.eacodingchallenge.databinding.ActivityMainBinding
import com.app.eacodingchallenge.models.Root
import com.app.eacodingchallenge.other.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: RecyclerViewAdapter
    private val list = mutableListOf<Root>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RecyclerViewAdapter(this, list)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        mainViewModel.res.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.textViewError.visibility = View.GONE
                    it.data?.let { data -> adapter.updateList(data) }
                }

                Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    binding.textViewError.visibility = View.GONE
                }

                Status.ERROR -> {
                    binding.progress.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                    binding.textViewError.visibility = View.VISIBLE
                }
            }
        }
    }
}