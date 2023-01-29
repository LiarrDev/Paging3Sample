package com.example.paging3sample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.paging3sample.adapter.RepoAdapter
import com.example.paging3sample.adapter.RetryAdapter
import com.example.paging3sample.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    private val repoAdapter = RepoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = repoAdapter.withLoadStateFooter(
            RetryAdapter { repoAdapter.retry() }
        )
        binding.recycler.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        lifecycleScope.launch {
            viewModel.items.collect {
                repoAdapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            repoAdapter.loadStateFlow.collect {
                binding.progressPrepend.isVisible = it.source.prepend is LoadState.Loading
                binding.progressAppend.isVisible = it.source.append is LoadState.Loading
                if (it.refresh is LoadState.Error) {
                    Toast.makeText(
                        this@MainActivity,
                        "Load Error: ${(it.refresh as LoadState.Error).error.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}