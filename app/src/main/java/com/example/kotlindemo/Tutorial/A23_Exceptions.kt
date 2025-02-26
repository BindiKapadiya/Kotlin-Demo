package com.example.kotlindemo.Tutorial

class A23_Exceptions {

    var arr = arrayOf("1", "2", "3")

    fun main() {

        try {
            println(arr[5])
        } catch (e: IndexOutOfBoundsException) {
            println("IndexOutOfBoundsException :: ${e.message}")
        } catch (e: Exception) {
            println("Exception :: ${e.message}")
        } finally {
            println("I will definitely call")
        }
    }
}