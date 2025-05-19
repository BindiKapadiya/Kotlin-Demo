package com.example.kotlindemo.Tutorial

class A20_DataClasses {
    val TAG = A20_DataClasses::class.simpleName

    fun main() {
        val p1 = Person(1, "Bindi",27)
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

    data class Person(val id: Int, val name: String, var age: Int = 23) {
        var _age: Int = age
            set(value) {
                require(value in 23..25) {
                    "Age must be between 23 and 25"
                }
                field = value
            }

        init {
            require(age in 23..25) {
                "Age must be between 23 & 25"
            }
        }
    }
}