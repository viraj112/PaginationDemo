package com.neosoft.paginationdemo.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.neosoft.paginationdemo.R
import com.neosoft.paginationdemo.databinding.ActivityFlowApiBinding
import com.neosoft.paginationdemo.model.Post
import com.neosoft.paginationdemo.paging.PostAdapter
import com.neosoft.paginationdemo.repository.PostRepository
import com.neosoft.paginationdemo.viewmodel.PostViewModel


class FlowApiActivity : AppCompatActivity() {
    lateinit var binding: ActivityFlowApiBinding
    private lateinit var postAdapter: PostAdapter
    private lateinit var postViewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_flow_api)

        //flow on :-we provides dispatchers inside it
        /*  //flowof : alternaive for flow staructure
          val data = listOf(1, 2, 3, 43, 4, 45).asFlow().flowOn(Dispatchers.IO)

          //map operator
          runBlocking {
              data.map { value ->
                  value * value
              }.collect {
                  Log.d("FlowApi", "onCreate: $it")
              }
          }

          //filter openrator
          //filter data by applying funtion on it

          runBlocking {
              data.filter {value ->
                  value %2 ==0
              }.collect{
                  Log.d("FlowApi", "onCreate: $it")
              }
          }*/
        initUi()
       setObservers()

    }



    private fun initUi() {
        val postViewModelFactory = PostViewModelFactory(PostRepository())
        postViewModel = ViewModelProvider(this, postViewModelFactory)[PostViewModel::class.java]
        postViewModel.getPost()

        postAdapter = PostAdapter(this, ArrayList())
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = postAdapter
        }

    }

    private fun setObservers() {
        postViewModel.postData.observe(this, Observer {
            postAdapter.setPostData(it as ArrayList<Post>)
            binding.progressBar.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        })
    }

}