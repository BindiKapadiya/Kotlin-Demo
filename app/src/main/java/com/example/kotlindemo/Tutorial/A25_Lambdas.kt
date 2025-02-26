package com.example.kotlindemo.Tutorial

import kotlin.math.pow

class A25_Lambdas {

    fun main() {
        println(sum(125.0, 125.0))
        println(power(3.0, 4.0))

        var fn: (a: Double, b: Double) -> Double = ::sum
        println(fn(320.0, 340.0))

        // Higher Order Function
        calculator(100.0, 200.0, ::sum)

        // Lamda
        val lamda1 = { x: Int, y: Int -> x + y }
        lamda1(45, 90)

        val multiLineLamda = {
            println("Hello Lambda")
            val x = 10 + 40
            20.0
            "Hello everyone"
        }

        println(multiLineLamda())

        var sumLamda = { a: Double, b: Double -> a + b }
        println(sumLamda(456.0, 456.0))

        // Simplify Lamda
        var singleParam: (Int) -> Int = { a: Int -> a * a }
        var simplifySingleParam: (Int) -> Int = { it * it }

        calculator(4.0, 5.0, { a, b -> a + b })
        calculator(4.0, 5.0) { a, b -> a + b } // We can write like this when last argument is function

    }

    fun sum(a: Double, b: Double) = a + b

    fun power(a: Double, b: Double): Double {
        return a.pow(b)
    }

    fun calculator(a: Double, b: Double, fn: (Double, Double) -> Double) {
        val result = fn(a, b)
        println(result)
    }
}