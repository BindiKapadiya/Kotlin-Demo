package com.example.kotlindemo.Services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.kotlindemo.BaseActivity
import com.example.kotlindemo.R
import com.example.kotlindemo.SplashActivity
import kotlin.concurrent.thread

class MyForegroundService : Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        thread(start = true) {
            while (true) {
                Log.e(BaseActivity.TAG, "Logging..")
                Thread.sleep(1000)
            }
        }
        startMyForegroundService()
        return super.onStartCommand(intent, flags, startId)
    }

    fun startMyForegroundService() {
        createNotificationChannel()
        val notification = createNotification()
        startForeground(111, notification)
    }

    fun getPendingIntent(): PendingIntent {
        val intent = Intent(this, SplashActivity::class.java)
        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    fun createNotificationChannel(): NotificationChannel {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("ID", "MY NOTIFICATION", NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager =
                ContextCompat.getSystemService(this, NotificationManager::class.java)
            notificationManager!!.createNotificationChannel(channel)
            return channel
        } else {
            TODO("VERSION.SDK_INT < O")
        }
    }

    private fun createNotification(): Notification {
        val notification = NotificationCompat.Builder(this, "ID")
            .setContentText("Foreground service running")
            .setContentTitle("Testing")
            .setContentIntent(getPendingIntent())
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true)
            .build()
        return notification
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}