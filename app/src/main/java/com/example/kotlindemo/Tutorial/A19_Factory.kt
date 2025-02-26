package com.example.kotlindemo.Tutorial

class A19_Factory {

    fun main() {
        var pizza1 = Pizza.Factory.create("Peppy Paneer")
        println(pizza1)

        var pizza2 = Pizza.create("Tomato")
        println(pizza2)

        // We want everyone needs to call create method
        // for that we will mark the constructor as private
//        val pizza3 = Pizza("Regular","Cheese burst")
    }

    class Pizza private constructor(val type: String, var toppings: String) {

        companion object Factory {
            fun create(pizzaType: String): Pizza {
                return when (pizzaType) {
                    "Tomato" -> Pizza("Tomato", "Tomato Cheese")
                    "Peppy Paneer" -> Pizza("Panner Farm", "Paneer, Cheese burst, Tomato, Onion")
                    else -> Pizza("Basic", "Onion cheese")
                }
            }
        }

        override fun toString(): String {
            return "Pizza(type='$type', toppings='$toppings')"
        }
    }
}