package com.example.kotlindemo.Services

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.kotlindemo.BaseActivity
import com.example.kotlindemo.R
import java.util.concurrent.TimeUnit

class WorkManagerActivity : BaseActivity(), View.OnClickListener {

    private val workManager = WorkManager.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_work_manager)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                100
            )
        }
    }

    private fun doWork() {
        // WhatsApp backup feature, Google photos sync

        val periodicWorkRequest =
            PeriodicWorkRequest.Builder(DemoWorker::class.java, 15, TimeUnit.MINUTES).build()

        val request = OneTimeWorkRequest.Builder(DemoWorker::class.java)
            .setConstraints(
                Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
            )
            .setBackoffCriteria(    // For retry
                BackoffPolicy.LINEAR,  // 10,20,30,40
//                BackoffPolicy.EXPONENTIAL,  // 10,20,40,80
                10,
                TimeUnit.SECONDS
            )
            .build()
        workManager.enqueue(request)
//        workManager.beginWith(request).then(request).then(request).enqueue()  // Sequence - define chaining

        workManager.getWorkInfoByIdLiveData(request.id).observe(this) {
            if (it != null) {
                printStatus(it.state.name)
            }
        }
    }

    private fun printStatus(name: String) {
        Log.e(TAG, name)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnStartService -> startService(Intent(this, LoggerService::class.java))
            R.id.btnStopService -> stopService(Intent(this, LoggerService::class.java))

            R.id.btnStartDynamicNotification ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(Intent(this, OfferService::class.java))
                }

            R.id.btnStartForegroundService ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(Intent(this, MyForegroundService::class.java))
                }

            R.id.btnStopForegroundService -> stopService(
                Intent(
                    this,
                    MyForegroundService::class.java
                )
            )

            R.id.btnWorkManager -> doWork()
        }
    }

}