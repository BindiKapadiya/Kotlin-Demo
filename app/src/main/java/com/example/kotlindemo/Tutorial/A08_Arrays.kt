package com.example.kotlindemo.Tutorial

import android.util.Log

class A08_Arrays {

    val TAG = A08_Arrays::class.simpleName
    val array = arrayOf("One", "Two", "Three")
    val array1 = arrayOf<Int>(1, 2, 3, 4)

    fun main() {
        for (i in array1) {
            Log.e(TAG, "$i")
        }

        for ((i, j) in array.withIndex()) {
            Log.e(TAG, "$i - $j")
        }

        Log.e(TAG, "${array[0]}")
        Log.e(TAG, "${array.get(1)}")
        Log.e(TAG, "${array.get(2)}")
        array.set(1, "Bindi")
        Log.e(TAG, "${array.get(1)}")
        Log.e(TAG, "${array.size}")
//        Log.e(TAG, "${array.get(3)}")
    }
}