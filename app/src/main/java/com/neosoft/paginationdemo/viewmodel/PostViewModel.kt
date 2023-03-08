package com.neosoft.paginationdemo.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.neosoft.paginationdemo.model.Post
import com.neosoft.paginationdemo.repository.PostRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PostViewModel(private val postRepository: PostRepository) : ViewModel() {

    val postData: MutableLiveData<List<Post>> = MutableLiveData()

    fun getPost() {
        viewModelScope.launch {
            postRepository.getPost()
                .catch { e ->
                    Log.d("main", "getPost: ${e.message}")
                }
                .collect { postData1 ->
                    delay(1000)
                    postData.postValue(postData1)
                }
        }
    }


//     val postData1: LiveData<List<Post>> = liveData {
//          postRepository.getPost()
//              .catch {  }
//              .collect {postData->
//                  emit(postData)
//              }
//      }




//     val postData2:LiveData<List<Post>> = postRepository.getPost()
//         .catch {  }
//         .asLiveData()

}