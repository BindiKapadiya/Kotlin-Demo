package com.example.kotlindemo.Tutorial

import android.util.Log

class A01_Variables {

    val TAG = A01_Variables::class.simpleName
    var score = 5   // can be assigned
    val result = 5  // can't be reassigned

    fun main() {
        Log.e(TAG, "" + score)
        score = 10
        Log.e(TAG, "" + score)

        Log.e(TAG, "" + result)
    }
}