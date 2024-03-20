package com.luiscustodio

import strikt.api.expectCatching
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import strikt.assertions.isFailure
import kotlin.test.Test

class PlaceholderFunctionTest {
    private val mars = Mars(Pair(2, 2))

    @Test
    fun `it should be able to move forwards`() {
        val rover = mars.welcomeIncomingRover(Pair(1, 1))
        rover.moveForward()

        expectThat(rover.position).isEqualTo(Pair(2, 1))
    }

    @Test
    fun `it should be able to move forwards twice in a row`() {
        val rover = mars.welcomeIncomingRover(Pair(1, 1))
        rover.moveForward()
        rover.moveForward()

        expectThat(rover.position).isEqualTo(Pair(3, 1))
    }

    @Test
    fun `it should be able to turn left and forward`() {
        val rover = mars.welcomeIncomingRover(Pair(1, 1))
        rover.turn('L')
        rover.moveForward()

        expectThat(rover.position).isEqualTo(Pair(1, 0))
    }

    @Test
    fun `it should be able to move east`() {
        val rover = mars.welcomeIncomingRover(Pair(1, 1))
        rover.turn('R')
        rover.moveForward()

        expectThat(rover.position).isEqualTo(Pair(1, 2))
    }

    @Test
    fun `it should be able to move south`() {
        val rover = mars.welcomeIncomingRover(Pair(1, 1))
        rover.turn('R')
        rover.turn('R')
        rover.moveForward()

        expectThat(rover.position).isEqualTo(Pair(0, 1))
    }

    @Test
    fun `rover should land in a valid planet location`() {
        expectCatching { mars.welcomeIncomingRover(Pair(5, 5)) }
            .isFailure()
            .isA<FailureToLandInMarsException>()
    }

    @Test
    fun `mars coordinates should have the maximum value of 50 `() {
        expectCatching { Mars(Pair(1, 51)) }
            .isFailure()
            .isA<NoSuchBigPlanetExistsException>()
    }
}

class NoSuchBigPlanetExistsException(message: String) : Exception(message)

class FailureToLandInMarsException(message: String) : Exception(message)

class Mars(private val planetSize: Pair<Int, Int>) {
    init {
        if (planetSize.first > 50 || planetSize.second > 50) {
            throw NoSuchBigPlanetExistsException("Planet dimensions must not exceed 50 in any direction.")
        }
    }

    fun welcomeIncomingRover(startingPoint: Pair<Int, Int>): Rover {
        if (startingPoint.first > planetSize.first || startingPoint.second > planetSize.second) {
            throw FailureToLandInMarsException("Starting point is outside the boundaries of Mars.")
        }
        return Rover(startingPoint, this)
    }
}

class Rover(startingPosition: Pair<Int, Int>, mars: Mars) {
    var position = startingPosition
    private var direction: Direction = Direction.NORTH

    fun moveForward() {
        position =
            when (direction) {
                Direction.NORTH -> Pair(position.first + 1, position.second)
                Direction.EAST -> Pair(position.first, position.second + 1)
                Direction.SOUTH -> Pair(position.first - 1, position.second)
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
