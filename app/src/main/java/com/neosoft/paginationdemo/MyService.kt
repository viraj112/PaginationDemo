package com.neosoft.paginationdemo

import android.R
import android.app.*
import android.app.Notification.Builder
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi


class MyService : Service() {
    private val AA = "MyService"
    private val CHANNEL_ID = "NOTIFICATION_CHANNEL"
    lateinit var manager:NotificationManager

    init {
        isServiceRunning = false
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(AA, "onCreate called")
        createNotificationChannel()
        isServiceRunning = true
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(AA, "onStartCommand called")
        val notificationIntent = Intent(this, WorkManagerActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, 0
        )
        val notification: Notification = Builder(this, CHANNEL_ID)
            .setContentTitle("Service is Running")
            .setContentText("Listening for Screen Off/On events")
            .setSmallIcon(R.drawable.ic_delete)
            .setContentIntent(pendingIntent)
            .setColor(resources.getColor(R.color.darker_gray))
            .build()
        startForeground(1, notification)
        return START_STICKY
        manager.cancelAll()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val appName ="asdasd"
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                appName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
             manager = getSystemService(
                NotificationManager::class.java
            )
            manager.createNotificationChannel(serviceChannel)
        }
    }

    override fun onDestroy() {
        Log.d(AA, "onDestroy called")
        isServiceRunning = false
        stopForeground(true)

        // call MyReceiver which will restart this service via a worker
        val broadcastIntent = Intent(this, MyReceiver::class.java)
        sendBroadcast(broadcastIntent)
        super.onDestroy()
    }

    companion object {
        var isServiceRunning: Boolean = false


    }


}