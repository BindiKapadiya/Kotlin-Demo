package com.example.kotlindemo.Tutorial

class A14_Interface {

    val TAG = A14_Interface::class.simpleName

    fun main() {
        dragObjects(arrayOf(Circle(4.0), Square(5.0), Triangle(2.0, 6.0), Person("Bindi")))
    }

    private fun dragObjects(objects: Array<Draggable>) {
        for (obj in objects) {
            println("${obj.drag()}")
        }
    }

    interface Draggable {
        fun drag()
    }

    abstract class Shape : Draggable {
        abstract fun area(): Double
    }

    class Circle(val radius: Double) : Shape() {
        override fun area(): Double = Math.PI * radius * radius
        override fun drag() {
            println("Circle is dragging")
        }
    }

    class Square(val side: Double) : Shape() {
        override fun area(): Double = side * side
        override fun drag() {
            println("Square is dragging")
        }
    }

    class Triangle(val base: Double, val height: Double) : Shape() {
        override fun area(): Double = 0.5 * base * height
        override fun drag() {
            println("Triangle is dragging")
        }
    }

    class Person(val name: String) : Draggable {
        override fun drag() {
            println("$name is dragging")
        }
    }
}