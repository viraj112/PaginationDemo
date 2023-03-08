package com.neosoft.paginationdemo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.neosoft.paginationdemo.model.Post
import com.neosoft.paginationdemo.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class QuoteViewModel @Inject constructor(private val repository: QuoteRepository) : ViewModel() {
    val list = repository.getQuotes().cachedIn(viewModelScope)
    val postData: MutableLiveData<List<Post>> = MutableLiveData()

    fun getPost()
    {
        viewModelScope.launch {
            repository.getPost()
                .catch { e->
                    Log.d("main", "getPost: ${e.message}")
                }
                .collect {postData1->
                    postData.value=postData1
                }
        }
    }

}