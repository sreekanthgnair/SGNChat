package com.sgncreations.sgnchat


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest{

    @Test
    fun emptyUsernameReturnsFalse() {
        val result = RegistrationUtil.validateRegistrationInput(
            "",
            "123",
            "12"
        )
        assertThat(result).isFalse()

    }
}