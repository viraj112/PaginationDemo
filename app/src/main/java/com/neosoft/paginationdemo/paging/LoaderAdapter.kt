package com.neosoft.paginationdemo.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.view.menu.MenuView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.neosoft.paginationdemo.R

class LoaderAdapter : LoadStateAdapter<LoaderAdapter.LoadViewholder>() {
    override fun onBindViewHolder(holder: LoadViewholder, loadState: LoadState) {
        holder.bind(loadState)

    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.loader_items,parent,false)
        return LoadViewholder(view)
    }

    class LoadViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val progressBar = itemView.findViewById<ProgressBar>(R.id.progress_bar)

        fun bind(loadState: LoadState) {
            progressBar.isVisible = loadState is LoadState.Loading
        }
    }


}