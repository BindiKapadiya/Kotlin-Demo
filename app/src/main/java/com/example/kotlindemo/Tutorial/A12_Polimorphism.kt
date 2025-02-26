package com.example.kotlindemo.Tutorial

import android.util.Log

class A12_Polimorphism {

    val TAG = A12_Polimorphism::class.simpleName

    fun main() {
        val circle: Shape = Circle(4.0)     // Directly reference to parent class
        val square: Shape = Square(4.0)

        Log.e(TAG, "${circle.toString()}")
        val shapes = arrayOf(Circle(4.0), Square(4.0), Triangle(4.0, 5.0))
        calculateAreas(shapes)
    }

    fun calculateAreas(shapes: Array<Shape>) {
        for (shape in shapes) {
            Log.e(TAG, "${shape.area()}")
        }
    }

    open class Shape() {
        open fun area(): Double {
            return 0.0
        }

        override fun toString(): String {
            return "I'm a Shape"
        }
    }

    class Circle(val radius: Double) : Shape() {
        override fun area(): Double {
            return Math.PI * radius * radius
        }
    }

    class Square(val side: Double) : Shape() {
        override fun area(): Double = side * side
    }

    class Triangle(val base: Double, val height: Double) : Shape() {
        override fun area(): Double {
            return 0.5 * base * height
        }
    }

}