package com.neosoft.paginationdemo.coroutines

import android.util.Log
import kotlinx.coroutines.*
import java.io.IOException

class CoroutinesRepository {
    fun returnSomeItems(result: (human: List<Human>, animal: List<Animal>) -> Unit) {

        CoroutineScope(Dispatchers.IO).launch {
            val humansResponse = async {
                Log.e("calling", "humans")
                getHumans()
            }
            val humans = humansResponse.await()


            if (humansResponse.isCompleted) {
                val animalsResponse = async {
                    Log.e("calling", "animals")
                    getAnimals()
                }
                Log.e("calling", "await")
                val animals = animalsResponse.await()
                result(humans, animals)
            }


//
//            if (humansResponse.isCompleted){
//            }


        }


    }

    suspend fun   getHumans(): List<Human> {

        val list = mutableListOf<Human>()
        for (i in 1..10) {

            list.add(
                Human(
                    "Putin",
                    "Russian"
                )
            )
        }
        delay(3000)
        Log.e("calling ", "get humans")
        return list
    }

    suspend fun getAnimals(): List<Animal> {

        val list = mutableListOf<Animal>()
        for (i in 1..10) {

            list.add(
                Animal(
                    "Bear",
                    "Vodka"
                )
            )
        }
        //delay(3000)
        Log.e("calling ", "get animals")
        return list
    }
}