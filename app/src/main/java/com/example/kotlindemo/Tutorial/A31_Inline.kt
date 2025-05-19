package com.example.kotlindemo.Tutorial

class A31_Inline {

    fun main() {
        doSomething { println("Doing work...") }
        process(
            inlineLambda = {
                println("Gets inlined")
            },
            noInlineLambda = {
                println("Stays as a normal function")
            }
        )
        execute {
            println("Gets inlined")
        }

        checkType<String>("Bindi")
        checkType<Int>(123)
    }

    // TODO copies the function's code directly here
    private inline fun doSomething(action: () -> Unit) {
        println("Before")
        action()
        println("After")

//        println("Before")
//        println("Doing work...")
//        println("After")
    }

    private inline fun process(inlineLambda: () -> Unit, noinline noInlineLambda: () -> Unit) {
        inlineLambda()  // gets inlined
        noInlineLambda()    // stays as a normal function object

//        val action = inlineLambda
        val action = noInlineLambda  // Only possible because of noinline
    }

    private inline fun execute(crossinline action: () -> Unit) {
        val runnable = Runnable {
            action()    // The action is inlined, but you can't write return inside action
        }
        Thread(runnable).start()

//        button.setOnClickListener {
//            action()
//        }
    }

    inline fun <reified T> checkType(value: Any) {
        if (value is T) {
            println("The value is of type ${T::class.simpleName}")
        } else {
            println("Not of type ${T::class.java}")
        }
    }
}