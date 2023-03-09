package com.neosoft.paginationdemo.coroutines

import android.util.Log
import androidx.lifecycle.ViewModel

class CoroutinesViewModel :ViewModel(){


    val repository: CoroutinesRepository= CoroutinesRepository()

     fun doSomeApiCalls() {

        repository.returnSomeItems{human, animal ->
            Log.e("humans", human.toString())
            Log.e("animals", animal.toString())
        }
    }
}