package com.example.kotlindemo.Tutorial

import kotlin.properties.Delegates

class A32_Interview {


    fun main() {
        val numbers = listOf(1, 9, 3, 5, 6)
        val sorted: List<Int> = numbers.sorted()
        val last = sorted.last()
        println("Largest number is:  $last")
        println("Largest number iss :  ${numbers.distinct().max()}")

        val sortedList = numbers.let {
            println(it.sorted())
            it.sorted()
        }

        val filter = sortedList.map {
            it * 2
        }.filter {
            it % 3 == 0
        }
        filter.forEach {
            println("Map data : $it")
        }

        val numArray = arrayOf(23, 45, 78, 21, 43)
        val largest = numArray.max()
        println("Largest number is:  $largest")

        // distinct() -> remove duplicates elements from array
        val sortedDescending = numArray.distinct().sortedDescending()
        val last1 = sortedDescending[1]
        println("Second Largest number is:  $last1")


        val square: (Int) -> Int = {
            it * it
        }
        println("Square : ${square(4)}")

        val square2 = { number: Int -> number * number }
        println("Square2 : ${square2(4)}")


        val arr = arrayOf(10, 20, 30)
        arr[0] = 50
        arr.forEach {
            println(it)
        }

        // TODO vetoable – Change can be rejected
        var age: Int by Delegates.vetoable(18) { _, _, newValue ->
            println("NewValue : $newValue")
            newValue >= 18
        }
        age = 3
        println("Age : $age")
        age = 120
        println("Age : $age")


        // TODO observable – For observing changes
        var name: String by Delegates.observable("Hello") { _, oldValue, newValue ->
            println("$oldValue -> $newValue")
        }
        name = "Bindi"
        name = "Kapadiya"
        name = "Kashyap"
//        println("Name : $name")
    }
}
