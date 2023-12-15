package com.example.unittesting

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class HomeworkTest {
    @Test
    fun `fibonacci 0 returns 0`() {
        val result = Homework.fib(0)
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `fibonacci 1 returns 1`() {
        val result = Homework.fib(1)
        assertThat(result).isEqualTo(1)
    }

    @Test
    fun `fibonacci 2 returns 1`() {
        val result = Homework.fib(2)
        assertThat(result).isEqualTo(1)
    }

    @Test
    fun `fibonacci 3 returns 2`() {
        val result = Homework.fib(3)
        assertThat(result).isEqualTo(2)
    }

    @Test
    fun `fibonacci -1 returns 1`() {
        val result = Homework.fib(-1)
        assertThat(result).isEqualTo(1)
    }

    @Test
    fun `check braces returns true`() {
        val result = Homework.checkBraces("(a * b)")
        assertThat(result).isTrue()
    }

    @Test
    fun `check braces returns false`() {
        val result = Homework.checkBraces("(a * b))")
        assertThat(result).isFalse()
    }

    @Test
    fun `check braces returns true for empty string`() {
        val result = Homework.checkBraces("")
        assertThat(result).isTrue()
    }

    @Test
    fun `check braces returns false for string with only one brace`() {
        val result = Homework.checkBraces("(")
        assertThat(result).isFalse()
    }
}
