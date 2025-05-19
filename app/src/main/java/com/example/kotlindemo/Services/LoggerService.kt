package com.example.kotlindemo.Services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.kotlindemo.BaseActivity
import kotlin.concurrent.thread

class LoggerService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.e(BaseActivity.TAG, "onCreate: ")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(BaseActivity.TAG, "onStartCommand: ")
        thread(start = true) {
            while (true) {
                Log.e(BaseActivity.TAG, "Logging Message..")
                Thread.sleep(1000)
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.e(BaseActivity.TAG, "onBind: ")
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(BaseActivity.TAG, "onDestroy: ")
    }
}