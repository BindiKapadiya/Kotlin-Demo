package com.example.kotlindemo.Tutorial

import android.util.Log
import kotlin.math.pow

class A07_Functions {

    val TAG = A07_Functions::class.simpleName

    fun main() {
        Log.e(TAG, "${add(12, 12)}")     // Arguments
        Log.e(TAG, "${add2(22, 12)}")
        Log.e(TAG, "${add3(32, 12)}")

        printMessage()
        printMessage(5)

        Log.e(TAG, "${addition(3, 4)}")
        Log.e(TAG, "${addition(a1 = 6, b1 = 8)}")     // Named arguments
        Log.e(TAG, "${addition(b1 = 12.0, a1 = 7.2)}")

        Log.e(TAG, "${power(2.0, 8.0)}")

        val funResult: (num1: Double, num2: Double) -> Double = ::power
        Log.e(TAG, "${funResult(3.5, 4.6)}")

    }

    fun add(num1: Int, num2: Int): Int {    // Parameters - val type - can't be reassigned
        val result = num1 + num2
        return result
    }

    fun add2(num1: Int, num2: Int): Int = num1 + num2

    fun add3(num1: Int, num2: Int) = num1 + num2

    fun printMessage(count: Int = 3) {
        for (i in 1..count) {
            Log.e(TAG, "printMessage: $i")
        }
    }

    fun addition(a1: Int, b1: Int): Int {
        return a1 + b1
    }

    fun addition(a1: Double, b1: Double): Double {
        return a1 + b1
    }

    fun power(num1: Double, num2: Double): Double {
        return num1.pow(num2)
    }
}