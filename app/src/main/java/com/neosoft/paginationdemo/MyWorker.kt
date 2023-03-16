package com.neosoft.paginationdemo

import android.R
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.work.Worker
import androidx.work.WorkerParameters


class MyWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {
    private val context: Context
    private val AA = "MyWorker"

    init {
        this.context = context
    }

    override fun doWork(): Result {
        Log.d(AA, "doWork called for: " + this.getId())
        Log.d(AA, "Service Running: " + MyService.isServiceRunning)


        if (!MyService.isServiceRunning) {
            Log.d(AA, "starting service from doWork")
            val intent = Intent(context, MyService::class.java)
            ContextCompat.startForegroundService(context, intent)
        }
        return Result.success()
    }

    override fun onStopped() {
        Log.d(AA, "onStopped called for: " + this.getId())
        super.onStopped()
    }


}