package com.example.kotlindemo.Tutorial

class A22_NullSafety {

    fun main() {
        var gender: String = "Male"
        var gender2: String? = null
        var isAdult: Boolean? = null

        if (gender2 != null) {
            println("Check : $gender2.uppercase()")
        }

        println("Check :: $gender2?.uppercase()")

        gender2?.let {
            println("Line 1")
            println("Line 2 $gender2")
            println("Line 3 $it")
        }

        var selectedValue = gender2 ?: "NA"
        println("${selectedValue.uppercase()}")
        println("${gender2!!.uppercase()}")
    }
}