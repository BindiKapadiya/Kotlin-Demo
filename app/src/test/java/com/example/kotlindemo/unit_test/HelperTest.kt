package com.example.kotlindemo.unit_test

import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class HelperTest {

    lateinit var helper: Helper

    @Before
    fun setUp() {
        println("Before every test case")
        helper = Helper()
    }

    @After
    fun tearDown() {
        println("After every test case")
    }

    @Test
    fun isPalindrome() {
        // Arrange
        val helper = Helper()

        // Act
        val result = helper.palindrome("hello")

        // Assert
        assertEquals(false, result)
    }

    @Test
    fun isPalindrome_inputString_level_expectedTrue() {
        // Act
        val result = helper.palindrome("level")

        // Assert
        assertEquals(true, result)
    }
}