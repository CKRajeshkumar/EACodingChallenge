package com.app.multipletyperecyclerview.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.multipletyperecyclerview.databinding.ActivityMainBinding
import com.app.multipletyperecyclerview.models.Root
import com.app.multipletyperecyclerview.other.Status
import com.app.multipletyperecyclerview.ui.RecyclerViewAdapter.Companion.VIEW_TYPE_ONE
import com.app.multipletyperecyclerview.ui.RecyclerViewAdapter.Companion.VIEW_TYPE_TWO
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: RecyclerViewAdapter
    private val list = mutableListOf<Pair<Root, String?>>()

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
                    it.data.let { data ->
                        val result = data?.flatMap { o -> o.bands.map { i -> o to i.recordLabel } }
                            ?.sortedBy { sort -> sort.second }?.distinct()
                            ?.groupBy { group -> group.second }

                        result?.map { pair ->
                            var initialValue = "dummy"
                            pair.value.map { rootPair ->
                                if (pair.key != initialValue) {
                                    initialValue = pair.key.toString()
                                    list.add(
                                        Pair(
                                            Root(VIEW_TYPE_ONE, "", rootPair.first.name, rootPair.first.bands),
                                            rootPair.second
                                        )
                                    )

                                }
                                rootPair.first.bands.filter { filteredValue ->
                                    filteredValue.recordLabel == rootPair.second
                                }.map { band ->
                                    list.add(
                                        Pair(
                                            Root(
                                                VIEW_TYPE_TWO,
                                                band.name.toString(),
                                                rootPair.first.name,
                                                rootPair.first.bands
                                            ),
                                            rootPair.second
                                        )
                                    )
                                }
                            }
                        }
                        adapter.updateList(list)
                    }
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