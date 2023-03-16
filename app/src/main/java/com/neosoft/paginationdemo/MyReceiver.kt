package com.neosoft.paginationdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit


class MyReceiver :BroadcastReceiver() {
    private val AA = "MyReceiver"

    override fun onReceive(p0: Context?, p1: Intent?) {
//        val workManager = WorkManager.getInstance(p0!!)
//        val startServiceRequest: PeriodicWorkRequest = PeriodicWorkRequestBuilder<>().Builder(MyWorker::class.java)
//            .build()
//        workManager.enqueue(startServiceRequest)

        val SendNotification: PeriodicWorkRequest = PeriodicWorkRequest.Builder(
            MyWorker::class.java,
            16,
            TimeUnit.MINUTES
        )
            .addTag("Notification")
            .setInitialDelay(15, TimeUnit.SECONDS)
            .build()
        WorkManager.getInstance().enqueue(SendNotification)
    }
}