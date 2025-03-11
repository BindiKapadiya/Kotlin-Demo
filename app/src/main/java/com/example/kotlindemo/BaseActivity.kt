package com.example.kotlindemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    companion object {
        val TAG = BaseActivity::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "${this::class.simpleName} onCreate: Activity Created")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "${this::class.simpleName} onStart: Activity started")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "${this::class.simpleName} onResume: Activity resumed")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "${this::class.simpleName} onPause: Activity paused")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "${this::class.simpleName} onStop: Activity stopped")
    }


}