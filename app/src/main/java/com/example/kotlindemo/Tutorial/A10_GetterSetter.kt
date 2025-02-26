package com.example.kotlindemo.Tutorial

import android.util.Log
import java.util.Locale

class A10_GetterSetter {

    val TAG = A10_GetterSetter::class.simpleName

    fun main() {
        var p1 = Person("Bindi", 23)
        Log.e(TAG, "${p1.name} - ${p1.age}")
        p1.age = 20
        Log.e(TAG, "${p1.age}")
        p1.age = -90
        Log.e(TAG, "${p1.age}")
    }

    class Person(nameParam: String, ageParam: Int) {
        val TAG = Person::class.simpleName

        var name = nameParam
            get() {
                return field.uppercase()
            }

        var age = ageParam
            set(value) {
                if (value > 0) {
                    field = value
                } else {
                    Log.e(TAG, "Age can't be negative")
                }
            }

        // default getter setter
        var email: String = ""
            get() = field
            set(value) {
                field = value
            }
    }

}