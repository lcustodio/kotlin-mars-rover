package com.luiscustodio

import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class MainTest {
    @Test
    fun `it should create Mars and land some Rovers given text instruction`() {
        val instructions =
            """
            5 3
            1 1 E 
            RFRFRFRF

            3 2 N 
            FRRFLLFFRRFLL

            0 3 W 
            LLFFFLFLFL
            """.trimIndent()

        val expected =
            """
            1 1 E 

            3 3 N LOST

            2 3 S 
            """.trimIndent()

        val channel = MarsHoverCommunicationChannel()
        expectThat(channel.textHandler(instructions)).isEqualTo(expected)
    }
}
