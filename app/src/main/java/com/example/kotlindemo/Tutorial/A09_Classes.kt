package com.example.kotlindemo.Tutorial

import android.util.Log

class A09_Classes {
    val TAG = A09_Classes::class.simpleName

    fun main() {
        val mustang = Car("Mustang", "Petrol", 100)
        val beetle = Car("Beetle", "Diesel", 150)

        Log.e(TAG, mustang.type)
        mustang.driveCar()
        Log.e(TAG, beetle.kmRang.toString())
        beetle.driveCar()

        val car = Automobile("Car", 4, 5, true, "Petrol")
        val car2 = Automobile("Car2", "Petrol")
        val person = Person("A", 15)
        val person2 = Person("B", 25)
        Log.e(TAG, "$person.name --> ${person.canVote}")
    }

    class Car(val name: String, val type: String, var kmRang: Int) {
        val TAG = Car::class.simpleName

        fun driveCar() {
            Log.e(TAG, "$name Car is driving")
        }

        fun applyBreaks() {
            Log.e(TAG, "Applied breaks")
        }
    }

    // var or val -> its define properties
    // otherwise its called parameters
    class Automobile(
        val name: String,
        val tyres: Int,
        val maxSeating: Int,
        hasAitBags: Boolean,
        val enginType: String
    ) {
        val TAG = Automobile::class.simpleName

        init {
            Log.e(TAG, "$name is created")
        }

        init {
            Log.e(TAG, "2nd initializer block")
        }

        constructor(nameParam: String, enginParam: String) : this(nameParam, 4, 5, true, enginParam)

        var airBags = hasAitBags
        fun drive() {}
        fun applyBreaks() {}
    }

    class Empty {}

    class Person(nameParam: String, ageParam: Int) {
        val name: String = "$nameParam - Clan"
        val age: Int = ageParam
        val canVote: Boolean = ageParam > 18
    }
}