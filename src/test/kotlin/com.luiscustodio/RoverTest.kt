package com.luiscustodio

import com.luiscustodio.model.Mars
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

class RoverTest {
    private val mars = Mars(Pair(2, 2))

    @Test
    fun `it should be able to move forwards`() {
        val rover = mars.welcomeIncomingRover(Pair(1, 1))
        rover.moveForward()

        expectThat(rover.position).isEqualTo(Pair(2, 1))
    }

    @Test
    fun `it should be able to move forwards twice in a row`() {
        val mars = Mars(Pair(3, 3))
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
    fun `it should indicate if they become non-operational (falling a cliff)`() {
        val rover = mars.welcomeIncomingRover(Pair(1, 1))
        rover.turn('L')
        rover.moveForward()
        rover.moveForward()

        expectThat(rover.isOperational).isEqualTo(false)
    }
}
