package com.example.kotlindemo.Tutorial

class A15_Casting {
    fun main() {
        val circle = Circle(4.0)
        val square = Square(5.7)
        val player = Player("Bindi")
        val arr: Array<Any> = arrayOf(circle, square, player, Test())

        for (obj in arr) {
            if (obj is Circle) {
                obj.drag()
            } else {
//                (obj as Player).sayMyName()
            }
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

    class Player(val name: String) : Draggable {
        override fun drag() {
            println("$name is dragging")
        }

        fun sayMyName() {
            println("My name is $name")
        }

    }

    class Test {

    }
}