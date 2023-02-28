package com.neosoft.paginationdemo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neosoft.paginationdemo.model.Post
import com.neosoft.paginationdemo.repository.PostRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class PostViewModel(private val postRepository: PostRepository) : ViewModel() {

    val postData: MutableLiveData<List<Post>> = MutableLiveData()

    /*  way 1 */
    fun getPost()
    {
        viewModelScope.launch {
            postRepository.getPost()
                .catch { e->
                    Log.d("main", "getPost: ${e.message}")
                }
                .collect {postData1->
                    postData.value=postData1
                }
        }
    }

    /*
       way 2
     val postData1:LiveData<List<Post>> = liveData {
          postRepository.getPost()
              .catch {  }
              .collect {postData->
                  emit(postData)
              }
      }
     */

    /*
    way 3
     val postData2:LiveData<List<Post>> = postRepository.getPost()
         .catch {  }
         .asLiveData()
     */
}