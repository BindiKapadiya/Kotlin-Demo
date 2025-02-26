package com.example.kotlindemo.Tutorial

class A28_ScopeFunctions {

    fun main() {
        val employee = Employee()
        employee.age = 20
        employee.name = "Bindi"

        employee.apply {
            age = 30
            name = "Kashyap"
        }
        println(employee)


        println(employee.age)
        println(employee.name)

        // Do not use employee object repeatedly
        // If you need to perform multiple operation on single object then you can use let function
        // Return value wil be same as last expression value
        var x = employee.let {
            println(it.age)
            println(it.name)
            2
        }

        // Do not use emp2? every time
        val emp2: Employee? = null
        emp2?.age = 60
        emp2?.name = "A"

        emp2?.let {
            it.age = 8
            it.name = "L"
        }


        // With
       var withObj = with(employee) {
            age = 33
            name = "ABC"
            6
        }

        // RUN
        employee.run {
            age = 45
            name = "XYZ"
        }
    }

    data class Employee(var name: String = "", var age: Int = 18)
}