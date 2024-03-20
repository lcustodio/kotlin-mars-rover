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

    @Test
    fun `it should be able to move forwards twice in a row`() {
        val rover = Rover(Pair(1, 1))
        rover.move('F')
        rover.move('F')

        expectThat(rover.position).isEqualTo(Pair(3, 1))
    }
}

class Rover(startingPosition: Pair<Int, Int>) {
    var position = startingPosition

    fun move(c: Char) {
        position =
            when (c) {
                'F' -> Pair(position.first + 1, position.second)
                else -> Pair(position.first, position.second)
            }
    }
}
