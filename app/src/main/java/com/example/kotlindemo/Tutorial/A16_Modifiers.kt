package com.example.kotlindemo.Tutorial

class A16_Modifiers {
    fun main() {
        val a = A()
        val b = B()
    }

    open class A {
        public var aPublic: Int = 10        // Everywhere
        private var bPrivate: Int = 20      // With in file in top level declaration & with in class in class members
        internal var cInternal: Int = 30        // With in a module
        protected var dProtected: Int = 40      // N/A in top level & Subclasses in class members
    }

    class B : A() {
        fun test() {
            println(aPublic)
//            println(bPrivate)
            println(cInternal)
            println(dProtected)
        }
    }


}