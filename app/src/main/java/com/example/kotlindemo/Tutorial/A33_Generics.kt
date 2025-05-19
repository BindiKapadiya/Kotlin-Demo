package com.example.kotlindemo.Tutorial

class A33_Generics {

    fun main() {
        printValue("Hello")
        printValue(10)
    }

    fun <T> printValue(value: T) {
    }
}