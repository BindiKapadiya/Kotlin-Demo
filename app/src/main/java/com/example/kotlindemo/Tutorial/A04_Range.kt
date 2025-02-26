package com.example.kotlindemo.Tutorial

import android.util.Log

class A04_Range {

    val TAG = A04_Range::class.simpleName

    fun main() {
        val number = 5
        val result = number in 1..5  // 1,2,3,4,5
        val result1 = number in 1 until 5  // 1,2,3,4

        Log.e(TAG, "" + result + "   --->   " + result1)
    }
}