package com.example.kotlindemo.Tutorial

class A29_Generics {
    fun main() {
        val intContainer = IntContainer(5)
        println(intContainer.getValue())

        val stringContainer = StringContainer("Bindi")
        println(stringContainer.getValue())

        val container = Container<Int>(3)
        println(container.getValue())

        val container2 = Container<String>("Kapadiya")
        println(container2.getValue())

        add(2)
        add(1, 2, 3, 4, 5)
    }

    class Container<T>(var data: T) {

        fun setValue(value: T) {
            data = value
        }

        fun getValue(): T {
            return data
        }
    }

    class IntContainer(var data: Int) {

        fun setValue(value: Int) {
            data = value
        }

        fun getValue(): Int {
            return data
        }
    }

    class StringContainer(var data: String) {

        fun setValue(value: String) {
            data = value
        }

        fun getValue(): String {
            return data
        }
    }

    fun add(vararg values: Int) {
        var sum = 0
        for (i in values) {
            sum += i
        }
        println(sum)
    }
}