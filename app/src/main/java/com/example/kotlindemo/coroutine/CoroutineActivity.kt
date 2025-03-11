package com.example.kotlindemo.coroutine

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.kotlindemo.BaseActivity
import com.example.kotlindemo.R
import com.example.kotlindemo.SplashActivity
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import kotlin.concurrent.thread

class CoroutineActivity : BaseActivity() {

    lateinit var txtCounter: TextView
    lateinit var viewModel: CoroutineViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_coroutine)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtCounter = findViewById<TextView>(R.id.txtCounter)

//        launchVsAsync()   // launch & async

//        CoroutineScope(Dispatchers.Main).launch() { // job cancellation
//            execute()
//        }

//        GlobalScope.launch {
////            executeWithContextTask()
//            executeNonBlocking()
//        }

        executeViewModelAndLifecycle()

    }


//    ------------------------------------- Suspend functions -------------------------------------

    fun doAction(view: View) {
//        thread(start = true) {
//            executeLongRunningTask()
//        }

        CoroutineScope(Dispatchers.IO).launch {
            Log.e(TAG, "doAction: 1 - ${Thread.currentThread().name}")
//            executeLongRunningTask()
        }

        GlobalScope.launch(Dispatchers.Main) {
            Log.e(TAG, "doAction: 2 - ${Thread.currentThread().name}")
            task1()
        }

        GlobalScope.launch(Dispatchers.Main) {
            Log.e(TAG, "doAction: 2 - ${Thread.currentThread().name}")
            task2()
        }

        MainScope().launch(Dispatchers.Default) {
            Log.e(TAG, "doAction: 3 - ${Thread.currentThread().name}")
        }
    }

    private fun executeLongRunningTask() {
        for (i in 1..100000000L) {

        }
    }

    fun updateCounter(view: View) {
        txtCounter.text = "${txtCounter.text.toString().toInt() + 1}"
    }

    suspend fun task1() {
        Log.e(TAG, "STARTING TASK 1")
//        yield()
        delay(5000L)
        Log.e(TAG, "ENDING TASK 1")
    }

    suspend fun task2() {
        Log.e(TAG, "STARTING TASK 2")
        delay(2000L)
        Log.e(TAG, "ENDING TASK 2")
    }

//    ------------------------------------- Launch & Async -------------------------------------

    private fun launchVsAsync() {
        CoroutineScope(Dispatchers.IO).launch {
            printFollowers()
        }
    }

    private suspend fun printFollowers1() {
        var fbFollowers = 0
        var instaFollowers = 0
        val job = CoroutineScope(Dispatchers.IO).launch {
            fbFollowers = getFbFollowers()
        }
        val job1 = CoroutineScope(Dispatchers.IO).launch {
            instaFollowers = getInstagramFollowers()
        }
        job.join()
        job1.join()
        Log.e(TAG, "FbFollowers: $fbFollowers, InstagramFollowers: $instaFollowers")
    }

    private suspend fun printFollowers() {
        CoroutineScope(Dispatchers.IO).launch {
            val fbFollowers = async { getFbFollowers() }
            val instaFollowers = async { getInstagramFollowers() }
            Log.e(TAG, "Fb: ${fbFollowers.await()}, Insta: ${instaFollowers.await()}")
        }
    }

    private suspend fun printFollowers2() {
        val fb = CoroutineScope(Dispatchers.IO).async {
            getFbFollowers()
        }
        val insta = CoroutineScope(Dispatchers.IO).async {
            getInstagramFollowers()
        }
        Log.e(TAG, "Fb: ${fb.await()}, Insta: ${insta.await()}")
    }

    private suspend fun getFbFollowers(): Int {
        delay(1000)
        return 55
    }

    private suspend fun getInstagramFollowers(): Int {
        delay(1000)
        return 125
    }

//    ------------------------------------- Job & Cancellation -------------------------------------

    private suspend fun execute1() {
        val parentJob = GlobalScope.launch(Dispatchers.Main) {
            Log.e(TAG, "Parent job started")

            val childJob = launch(Dispatchers.IO) {
                Log.e(TAG, "Child job started")
                delay(5000)
                Log.e(TAG, "Child job end")
            }

            delay(3000)
            Log.e(TAG, "Parent job end")
        }

//        delay(1000)
//        parentJob.cancel()

        parentJob.join()
        Log.e(TAG, "Parent job completed")
    }

    // Child job cancel
    private suspend fun execute2() {
        val parentJob = GlobalScope.launch(Dispatchers.Main) {
            Log.e(TAG, "Parent job started")

            val childJob = launch(Dispatchers.IO) {
                try {
                    Log.e(TAG, "Child job started")
                    delay(5000)
                    Log.e(TAG, "Child job end")
                } catch (e: CancellationException) {
                    Log.e(TAG, "Child job cancelled")
                }
            }

            delay(3000)
            childJob.cancel()
            Log.e(TAG, "Parent job end")
        }

        parentJob.join()
        Log.e(TAG, "Parent job completed")
    }

    private suspend fun execute() {
        val parentJob = CoroutineScope(Dispatchers.IO).launch {
            for (i in 1..1000) {
                if (isActive) {
                    executeLongRunningTask()
                    Log.e(TAG, "execute: $i")
                }
            }
        }

        delay(1000)
        Log.e(TAG, "Cancelling Job")
        parentJob.cancel()

        parentJob.join()
        Log.e(TAG, "Parent job completed")
    }

//    ------------------------------------- WithContext & runBlocking -------------------------------------

    private suspend fun executeWithContextTask() {
        Log.e(TAG, "Before")
        withContext(Dispatchers.IO) {
            delay(1000)
            Log.e(TAG, "Inside")
        }
        Log.e(TAG, "After")
    }

    private suspend fun executeNonBlocking() {
        runBlocking {   // use while app is closing and some operation take time to perform.
            launch {
                delay(1000)
                Log.e(TAG, "World")
            }
            Log.e(TAG, "Hello")
        }
    }

//    ------------------------------------- ViewModelScope & LifeCycleScope -------------------------------------

    private fun executeViewModelAndLifecycle() {
        viewModel = ViewModelProvider(this).get(CoroutineViewModel::class.java)

        lifecycleScope.launch {
            delay(2000)
            val intent = Intent(this@CoroutineActivity, SplashActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}