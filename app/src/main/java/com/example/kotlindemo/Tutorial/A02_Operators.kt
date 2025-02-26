package com.example.kotlindemo.Tutorial

import android.util.Log

class A02_Operators {

    val TAG = A02_Operators::class.simpleName

    fun main() {

        // ------------ Arithmetic Operators ------------
        var i = 13
        var j = 2

        Log.e(TAG, "" + (i + j))
        Log.e(TAG, "" + (i - j))
        Log.e(TAG, "" + (i * j))
        Log.e(TAG, "" + (i / j))
        Log.e(TAG, "" + (i.toFloat() / j))
        Log.e(TAG, "" + (i % j))

        Log.e(TAG, "" + (i > j))
        Log.e(TAG, "" + (i < j))
        Log.e(TAG, "" + (i >= j))
        Log.e(TAG, "" + (i <= j))
        Log.e(TAG, "" + (i == j))
        Log.e(TAG, "" + (i != j))

        // ------------ Relational Operators ------------
        var a = 10

        // post increment
        Log.e(TAG, "" + a++)    // 10
        Log.e(TAG, "" + a)      // 11

        // pre increment
        Log.e(TAG, "" + ++a)    // 12
        Log.e(TAG, "" + a)      // 12

        Log.e(TAG, "" + a++ + ++a)      // 12 + 14 = 16


        // ------------ Logical Operators ------------
        val above70 = false
        val knowsProgramming = false

        // && AND
        var calledForInterview = above70 && knowsProgramming
        Log.e(TAG, "" + calledForInterview)

        // || OR
        calledForInterview = above70 || knowsProgramming
        Log.e(TAG, "" + calledForInterview)

        // !| NOT
        calledForInterview = !above70 || knowsProgramming
        Log.e(TAG, "" + calledForInterview)

    }

}