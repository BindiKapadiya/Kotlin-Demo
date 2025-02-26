package com.example.kotlindemo.Tutorial

import android.util.Log

class A13_Abstract {

    val TAG = A13_Abstract::class.simpleName

    fun main() {
        val circle = Circle(5.0)
        Log.e(TAG, "${circle.area()}")
        circle.display()
    }

    abstract class Shape {
        var name: String = ""
        abstract fun area(): Double
        abstract fun display()
    }

    class Circle(private val radius: Double) : Shape() {
        override fun area(): Double = Math.PI * radius * radius
        override fun display() {
            Log.e("Circle", "Circle is getting displayed")
        }
    }
}