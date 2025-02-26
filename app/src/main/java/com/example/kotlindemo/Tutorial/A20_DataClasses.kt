package com.example.kotlindemo.Tutorial

class A20_DataClasses {
    val TAG = A20_DataClasses::class.simpleName

    fun main() {
        val p1 = Person(1, "Bindi")
        val p2 = Person(1, "Bindi")

        println(p1)
        println(p2)
        println(p1.hashCode())
        println(p1 == p2)

        val p3 = p1.copy(id = 3, name = "Kashyap")
        println(p3)

        // Destructuring or components end function
        val (id, name) = p1
        println(id)
        println(name)
        println(p1.component1())
        println(p1.component2())
    }

    data class Person(val id: Int, val name: String)
}