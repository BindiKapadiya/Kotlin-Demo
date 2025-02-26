package com.example.kotlindemo.Tutorial

class A27_Extensions {
    fun main() {
        println("Hello everyone".formattedString())

        calculateTimeAndRun { loop(50000) }
    }

    // Extension function
    private fun String.formattedString(): String {
        return "----------------- \n$this\n-----------------"
    }


    fun loop(n: Long) {
        for (i in 1..n) {

        }
    }

    // Performance benefit
    // Place function's body at call time in decompile file
    inline fun calculateTimeAndRun(fn: () -> Unit) {
        val start = System.currentTimeMillis()
        fn()
        val end = System.currentTimeMillis()
        println("Time taken ${end - start} ms")
    }
}