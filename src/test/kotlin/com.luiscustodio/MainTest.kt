package com.luiscustodio

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

class PlaceholderFunctionTest {
    @Test
    fun `it should be able to move forwards`() {
        val rover = Rover(Pair(1, 1))
        rover.move('F')

        expectThat(rover.position).isEqualTo(Pair(2, 1))
    }
}

class Rover(startingPosition: Pair<Int, Int>) {
    val position = Pair(2, 1)

    fun move(c: Char) {}
}
