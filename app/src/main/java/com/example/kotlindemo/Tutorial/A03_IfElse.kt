package com.example.kotlindemo.Tutorial

import android.util.Log

class A03_IfElse {

    val TAG = A03_IfElse::class.simpleName
    val number = 10

    fun main() {
        // ------------------  STATEMENTS  ----------------
        if (number % 2 == 0)
            Log.e(TAG, "Even")
        else
            Log.e(TAG, "Odd")

        // ------------------  EXPRESSIONS  ----------------
        val result = if (number % 2 == 0) "Even" else "Odd"
        Log.e(TAG, "" + result)
    }

}