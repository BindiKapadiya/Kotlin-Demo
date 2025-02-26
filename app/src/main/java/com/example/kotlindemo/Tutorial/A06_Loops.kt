package com.example.kotlindemo.Tutorial

import android.util.Log

class A06_Loops {

    val TAG = A06_Loops::class.simpleName

    fun main() {
        var number = 5
        var index = 1

        //---------------  WHILE  ---------------
        while (index <= 10) {
            Log.e(TAG, "$number x $index = ${number * index}")
            index++
        }

        //--------------- DO WHILE  ---------------
        do {
            Log.e(TAG, "$number x $index = ${number * index}")
            index++
        } while (index <= 10)

        //--------------- FOR  ---------------

        for (i in 1..5) {      // counter plus by 1
            Log.e(TAG, "$i")      // 1,2,3,4,5
        }

        for (i in 1..5 step 2) {      // counter plus by 2
            Log.e(TAG, "" + i)         // 1,3,5
        }

        for (i in 1 until 5) {
            Log.e(TAG, "" + i)      // 1,2,3,4
        }

        for (i in 5 downTo 1) {
            Log.e(TAG, "" + i)      // 5,4,3,2,1
        }

        for (i in 5 downTo 1 step 2) {
            Log.e(TAG, "" + i)      // 5,3,1
        }

        val z = 2
        for (i in 1..10) {
//            Log.e(TAG, z.toString() + " X " + i + " = " + (z * i))
            // String templating
            Log.e(TAG, "$z X $i = ${z * i}")
        }
    }

}