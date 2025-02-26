package com.example.kotlindemo.Tutorial

class A26_CollectionFunctions {

    fun main() {
        val num = listOf(1, 2, 3, 4, 5)
        val num2: List<String> = listOf("1", "2", "3")

        // Filter
        println(num.filter { it % 2 != 0 })
        println(num2.filter { it.contains("2") })

        val userList = listOf(
            User(1, "Bindi"),
            User(2, "Sanky"),
            User(3, "Kashyap")
        )
        println(userList.filter { it.id == 2 })


        // Map this data to another form
        val list = num.map { it * it }
        println(list)

        val paidUseList = userList.map {
            PaidUser(it.id, it.name, "Paid")
        }
        println(paidUseList)


        // Foreach
        num.forEach {
            println(it)
        }
    }

    data class User(val id: Int, val name: String)
    data class PaidUser(val id: Int, val name: String, val type: String)

}