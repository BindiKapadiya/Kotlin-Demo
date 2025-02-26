package com.example.kotlindemo.Tutorial

class A21_EnumSealed {

    fun main() {

        // Enum
        val day = Days.MONDAY
        println(day)
        println(day.number)
        day.printFormattedDay()

        for (i in Days.entries) {
            println(i)
        }


        // Sealed
        val tile = Red("Mushroom", 25)
        val tile2 = Red("Fire", 30)
        println("${tile.points} - ${tile.type}")
    }

    enum class Days(val number: Int) {
        SUNDAY(1),
        MONDAY(2),
        TUESDAY(3),
        WEDNESDAY(4),
        THURSDAY(5),
        FRIDAY(6),
        SATURDAY(7);

        fun printFormattedDay() {
            println("Day is $this")
        }
    }

    sealed class Tiles
    class Red(val type: String, val points: Int) : Tiles()
    class Blue(val points: Int) : Tiles()

}