package com.luiscustodio

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

class PlaceholderFunctionTest {
    @Test
    fun `it should be able to move forwards`() {
        val rover = Rover(Pair(1, 1))
        rover.moveForward()

        expectThat(rover.position).isEqualTo(Pair(2, 1))
    }

    @Test
    fun `it should be able to move forwards twice in a row`() {
        val rover = Rover(Pair(1, 1))
        rover.moveForward()
        rover.moveForward()

        expectThat(rover.position).isEqualTo(Pair(3, 1))
    }

    @Test
    fun `it should be able to turn left and forward`() {
        val rover = Rover(Pair(1, 1))
        rover.turn('L')
        rover.moveForward()

        expectThat(rover.position).isEqualTo(Pair(1, 0))
    }

    @Test
    fun `it should be able to move east`() {
        val rover = Rover(Pair(1, 1))
        rover.turn('R')
        rover.moveForward()

        expectThat(rover.position).isEqualTo(Pair(1, 2))
    }
}

class Rover(startingPosition: Pair<Int, Int>) {
    var position = startingPosition
    private var direction: Direction = Direction.NORTH

    fun moveForward() {
        position =
            when (direction) {
                Direction.NORTH -> Pair(position.first + 1, position.second)
                Direction.EAST -> Pair(position.first, position.second + 1)
                Direction.SOUTH -> TODO()
                Direction.WEST -> Pair(position.first, position.second - 1)
            }
    }

    fun turn(c: Char) {
        direction =
            when (c) {
                'L' -> Direction.anticlockwise(direction)
                else -> Direction.clockwise(direction)
            }
    }
}

enum class Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST,
    ;

    companion object {
        fun anticlockwise(currentEnum: Direction): Direction {
            val values = entries.toTypedArray()
            val previousIndex = (currentEnum.ordinal - 1 + values.size) % values.size
            return values[previousIndex]
        }

        fun clockwise(currentEnum: Direction): Direction {
            val values = entries.toTypedArray()
            val nextIndex = (currentEnum.ordinal + 1) % values.size
            return values[nextIndex]
        }
    }
}
