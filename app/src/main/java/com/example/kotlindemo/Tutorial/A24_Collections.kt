package com.example.kotlindemo.Tutorial

class A24_Collections {

    val arr = listOf("11", "12", "13")
    val num = mutableListOf("1", "2", "3")

    val students = mutableMapOf<Int, String>()
    val map = mapOf<Int, String>(1 to "A", 2 to "B", 3 to "C", 4 to "D")

    fun main() {
        println(num.indexOf("2"))
        println(num.contains("4"))
        num[1] = "6"
        println(num)
        num.add("8")
        num.add("9")
        println(num)

        num.addAll(arr)
        println(num)


        students.put(1, "Bindi")
        students.put(2, "Kashyap")
        students.put(3, "Sanky")
        println(students[3])

        for ((key, value) in students) {
            println("$key = $value")
        }

        students[4] = "Kapadiya"
        println(students[5])
        println(students[4])

        for ((key, value) in map) {
            println("$key --> $value")
        }
    }
}