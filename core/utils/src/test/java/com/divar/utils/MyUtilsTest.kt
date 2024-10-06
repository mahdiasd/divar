package com.divar.utils


import org.junit.jupiter.api.Test

class MyUtilsTest {

    @Test
    fun remainingOfFour() {
        val result = MyUtils.remainingOfFour(5)
        assert(result == 8)
    }
}