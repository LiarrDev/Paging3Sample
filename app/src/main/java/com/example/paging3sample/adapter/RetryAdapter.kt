package com.example.paging3sample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3sample.databinding.ItemRetryBinding

class RetryAdapter(val retry: () -> Unit) : LoadStateAdapter<RetryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val binding = ItemRetryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.tvRetry.setOnClickListener { retry() }
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    class ViewHolder(private val binding: ItemRetryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.tvRetry.isVisible = loadState is LoadState.Error
            binding.progressCircular.isInvisible = (loadState is LoadState.Loading).not()
        }
    }
}