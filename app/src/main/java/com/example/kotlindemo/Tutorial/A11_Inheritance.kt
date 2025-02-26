package com.example.kotlindemo.Tutorial

import android.util.Log

class A11_Inheritance {

    val TAG = A11_Inheritance::class.simpleName

    fun main() {
        val smartPhone = SmartPhone()
        smartPhone.getDeviceInfo()
        smartPhone.display()
        Log.e(TAG, "main: " + smartPhone.toString())
    }

    // IS-A relationship (Circle is a shape, Square is a shape)

    open class Phone {
        val TAG = Phone::class.simpleName

        init {
            Log.e(TAG, "Phone --> Parent constructor ")
        }

        open val name: String = "Samsung"
        open val type: String = "Smart"
        val volume: Int = 10

        fun makeCall() {
            Log.e(TAG, "Phone --> makeCall: ")
        }

        open fun display() {
            Log.e(TAG, "Phone --> display: ")
        }

        fun powerOff() {
            Log.e(TAG, "Phone --> powerOff: ")
        }

        open fun getDeviceInfo() {
            Log.e(TAG, "Phone --> getDeviceInfo: ")
        }
    }

    class BasicPhone : Phone() {
        init {
            Log.e(TAG, "BasicPhone --> Child constructor ")
        }

        fun getScreenInfo() {
            Log.e(TAG, "BasicPhone --> getScreenInfo: ")
        }

        override fun display() {
            Log.e(TAG, "BasicPhone --> display: ")
        }

        override fun getDeviceInfo() {
            super.getDeviceInfo()
            Log.e(TAG, "BasicPhone --> getDeviceInfo: ")
        }


    }

    class SmartPhone : Phone() {
        init {
            Log.e(TAG, "SmartPhone --> Child constructor ")
        }

        fun playMovie() {
            Log.e(TAG, "SmartPhone --> playMovie: ")
        }

        fun takePicture() {
            Log.e(TAG, "SmartPhone --> takePicture: ")
        }

        fun getLocation() {
            Log.e(TAG, "SmartPhone --> getLocation: ")
        }

        override fun display() = println("SmartPhone --> display: ")

        override fun getDeviceInfo() {
            super.getDeviceInfo()
            Log.e(TAG, "SmartPhone --> getDeviceInfo: ")
        }

        override fun toString(): String {
            return "Name : $name --> Size : $type"
        }

    }
}