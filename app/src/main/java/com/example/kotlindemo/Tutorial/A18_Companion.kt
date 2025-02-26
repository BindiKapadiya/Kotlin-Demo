package com.example.kotlindemo.Tutorial

class A18_Companion {

    fun main() {
        MyClass.MyObject.f()
        MyClass.f()
        MyClass.AnotherObject.g()
    }

    class MyClass {
        companion object MyObject {
            @JvmStatic
            fun f() {
                println("Hello I am F from MyObject")
            }
        }

        object AnotherObject {
            fun g() {
                println("Hello I am G from Another Object")
            }
        }
    }
}