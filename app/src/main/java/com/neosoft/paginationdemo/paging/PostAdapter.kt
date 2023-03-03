package com.neosoft.paginationdemo.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.neosoft.paginationdemo.R
import com.neosoft.paginationdemo.databinding.EachRowBinding
import com.neosoft.paginationdemo.model.Post

class PostAdapter(private val context: Context, private var postList: ArrayList<Post>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = EachRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postList[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = postList.size
    class PostViewHolder(private val binding: EachRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.post = post
        }
    }

    fun setPostData(postList: ArrayList<Post>) {
        this.postList = postList
        notifyDataSetChanged()
    }
}