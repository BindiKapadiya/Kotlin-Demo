package com.example.kotlindemo.Tutorial

import android.util.Log

class A05_When {
    val TAG = A05_When::class.simpleName

    fun main() {
        // ------------------  STATEMENTS  ----------------
        val animal = "Dog"
        when (animal) {
            "Horse" -> Log.e(TAG, "Animal is Horse")
            "Cat" -> Log.e(TAG, "Animal is Cat")
            "Dog" -> Log.e(TAG, "Animal is Dog")
            else -> Log.e(TAG, "Animal not found")
        }

        // ------------------  EXPRESSIONS  ----------------
        val resultAnimal = when (animal) {
            "Horse" -> "Animal is Horse"
            "Cat" -> "Animal is Cat"
            "Dog" -> "Animal is Dog"
            else -> "Animal not found"
        }
        Log.e(TAG, "" + resultAnimal)

        // ------------------  RANGE EXPRESSIONS  ----------------
        val numberWhen = 13
        val resultWhen = when (numberWhen) {
            10 -> "Ten"
            11 -> "Eleven"
            12 -> "Twelve"
            in 13..19 -> "Teen"
            else -> "Not in range"
        }
        Log.e(TAG, "" + resultWhen)
    }
}