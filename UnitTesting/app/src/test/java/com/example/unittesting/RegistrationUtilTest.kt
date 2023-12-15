package com.example.unittesting

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest {

    @Test
    fun `empty username returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            username = "",
            password = "123",
            confirmedPassword = "123",
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated password returns true`() {
        val result = RegistrationUtil.validateRegistrationInput(
            username = "Philip",
            password = "123",
            confirmedPassword = "123",
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `username already exists returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            username = "Peter",
            password = "123",
            confirmedPassword = "123",
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            username = "Philip",
            password = "",
            confirmedPassword = "",
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password was repeated incorrectly returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            username = "Philip",
            password = "123",
            confirmedPassword = "1234",
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password contains less than 2 digits returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            username = "Philip",
            password = "abc1",
            confirmedPassword = "abc1",
        )
        assertThat(result).isFalse()
    }
}
