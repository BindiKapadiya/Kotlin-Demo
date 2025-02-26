package com.example.kotlindemo.Tutorial

class A30_Nesting {
    fun main() {

        var outer = Outer()
        outer.i

//        var nested = Outer.Nested()
//        nested.test()

        var nested2 = Outer().Nested()
        nested2.test()
    }

    class Outer {
        var i = 10

        inner class Nested() {
            fun test() {
                println("I am in nested class $i")
            }
        }
    }
}