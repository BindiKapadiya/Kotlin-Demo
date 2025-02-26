package com.example.kotlindemo.Tutorial

class A17_ObjectKeyword {

    fun main() {

        // Direct object without class
        val testObj = object {        // Anonymous object
            val x: Int = 10
            fun method() = println("I'm object expression")
        }
        testObj.method()


        // Implement interface directly to object without any class
        val cloneObj = object : Cloneable {
            override fun clone() {
                println("I am clone")
            }
        }
        cloneObj.clone()


        // Use object instance
        val objTest = Test.myMethod()

        // Inherit class to anonymous object
        val obj = object : Person("Bindi") {
            override fun fullName() {
                super.fullName()
                println("Anonymous - $name")
            }
        }

        obj.fullName()
    }

    interface Cloneable {
        fun clone()
    }

    open class Person(val name: String) {
        open fun fullName() = println("Full name : $name")
    }

    object Test {
        internal val x = 10
        fun myMethod() = println("I'm a object")
    }
}