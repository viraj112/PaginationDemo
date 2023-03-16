package com.neosoft.paginationdemo

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit


class WorkManagerActivity : AppCompatActivity() {
    lateinit var myrreceiver: MyReceiver
    private var filter: IntentFilter? = null
    private val AA = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)
        myrreceiver = MyReceiver()
        filter = IntentFilter()
       // LocalBroadcastManager.getInstance(this).registerReceiver(myrreceiver,filter!!)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            startServiceViaWorker()
           // startService()
            //registerReceiver(myrreceiver)
        }
    }



    fun onStartServiceClick(v: View?) {
        startService()
    }

    fun onStopServiceClick(v: View?) {
        stopService()
    }

    override fun onDestroy() {
        stopService()
        super.onDestroy()
    }

    fun startService() {
        Log.d(AA, "startService called")
        if (!MyService.isServiceRunning) {
            val serviceIntent = Intent(this, MyService::class.java)
            ContextCompat.startForegroundService(this, serviceIntent)
        }
    }

    fun stopService() {
        Log.d(AA, "stopService called")
        if (MyService.isServiceRunning) {
            val serviceIntent = Intent(this, MyService::class.java)
            stopService(serviceIntent)
        }
    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun startServiceViaWorker() {
        Log.d(AA, "startServiceViaWorker called")
        val UNIQUE_WORK_NAME = "StartMyServiceViaWorker"
        val workManager = WorkManager.getInstance(this)

        val request: PeriodicWorkRequest = PeriodicWorkRequest.Builder(
            MyWorker::class.java,
            15,
            TimeUnit.MINUTES
        )
            .build()
        workManager.enqueue(request)
//        workManager.enqueueUniquePeriodicWork(
//            UNIQUE_WORK_NAME,
//            ExistingPeriodicWorkPolicy.KEEP,
//            request
//        )
    }
}