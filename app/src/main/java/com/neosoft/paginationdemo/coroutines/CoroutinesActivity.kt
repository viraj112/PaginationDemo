package com.neosoft.paginationdemo.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.neosoft.paginationdemo.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


class CoroutinesActivity : AppCompatActivity() {

    lateinit var viewModel:CoroutinesViewModel
    var scope = CoroutineScope(SupervisorJob()+ Dispatchers.IO)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)
        viewModel = ViewModelProvider(this).get(CoroutinesViewModel::class.java)

        scope.launch {
            viewModel.doSomeApiCalls()
        }

    }
}