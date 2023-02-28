package com.neosoft.paginationdemo.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.neosoft.paginationdemo.repository.PostRepository
import com.neosoft.paginationdemo.viewmodel.PostViewModel

class PostViewModelFactory(private val postRepository: PostRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostViewModel(postRepository) as T
    }
}