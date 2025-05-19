package com.example.kotlindemo.Services

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.kotlindemo.BaseActivity.Companion.TAG

class DemoWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        performWork()
        return Result.retry()
    }

    fun performWork() {
        Thread.sleep(2000)
        Log.e(TAG, "Task completed")
    }
}