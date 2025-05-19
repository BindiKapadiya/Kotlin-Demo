package com.example.kotlindemo.Tutorial

class A32_Interview {


    fun main() {
        val numbers = listOf(1, 9, 3, 5, 6)
        val sorted: List<Int> = numbers.sorted()
        val last = sorted.last()
        println("Largest number is:  $last")

        val numArray = arrayOf(23, 45, 78, 21, 43)
        val largest = numArray.max()
        println("Largest number is:  $largest")

        // distinct() -> remove duplicates elements from array
        val sortedDescending = numArray.distinct().sortedDescending()
        val last1 = sortedDescending[1]
        println("Second Largest number is:  $last1")
    }
}
