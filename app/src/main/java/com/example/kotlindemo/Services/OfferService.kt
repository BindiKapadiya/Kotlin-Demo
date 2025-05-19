package com.example.kotlindemo.Services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.kotlindemo.R
import com.example.kotlindemo.SplashActivity

class OfferService : Service() {

    lateinit var handlerThread: HandlerThread
    lateinit var handler: Handler
    lateinit var notificationBuilder: NotificationCompat.Builder

    override fun onCreate() {
        super.onCreate()
        handlerThread = HandlerThread("OfferService")
        handlerThread.start()
        handler = Handler(handlerThread.looper)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startOfferForegroundService()
        handler.post {
            trackSeconds()
            stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    fun startOfferForegroundService() {
        notificationBuilder = getMyNotificationBuilder()
        createNotificationChannel()
        startForeground(111, notificationBuilder.build())
    }

    private fun trackSeconds() {
        Thread.sleep(10000)

        for (i in 10 downTo 0) {
            Thread.sleep(1000)

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationBuilder.setContentText("$i seconds to redeem the offer").setSilent(true)
            notificationManager.notify(111, notificationBuilder.build())
        }
    }

    fun getPendingIntent(): PendingIntent {
        val intent = Intent(this, SplashActivity::class.java)
        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    fun createNotificationChannel(): NotificationChannel {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(
                    "ChannelID",
                    "MY NOTIFICATION",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            val notificationManager =
                ContextCompat.getSystemService(this, NotificationManager::class.java)
            notificationManager!!.createNotificationChannel(channel)
            return channel
        } else {
            TODO("VERSION.SDK_INT < O")
        }
    }

    private fun getMyNotificationBuilder(): NotificationCompat.Builder {
        val notificationBuilder = NotificationCompat.Builder(this, "ChannelID")
            .setContentText("60% off on selected items")
            .setContentTitle("Offer you can't refuse")
            .setContentIntent(getPendingIntent())
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true)
        return notificationBuilder
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        handlerThread.quitSafely()
    }
}