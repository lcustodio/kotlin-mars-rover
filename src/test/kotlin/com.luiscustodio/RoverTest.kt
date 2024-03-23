package com.luiscustodio

import com.luiscustodio.model.Direction
import com.luiscustodio.model.Mars
import com.luiscustodio.service.SpaceStation
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

class RoverTest {
    private val spaceStation = SpaceStation()
    private val mars = Mars(Pair(2, 2), spaceStation)

    @Test
    fun `it should be able to move forwards`() {
        val rover = mars.welcomeIncomingRover(Pair(1, 1))
        rover.moveForward()

        expectThat(rover.position).isEqualTo(Pair(1, 2))
    }

    @Test
    fun `it should be able to move forwards twice in a row`() {
        val mars = Mars(Pair(3, 3), spaceStation)
        val rover = mars.welcomeIncomingRover(Pair(1, 1))
        rover.moveForward()
        rover.moveForward()

        expectThat(rover.position).isEqualTo(Pair(1, 3))
    }

    @Test
    fun `it should be able to turn left and forward`() {
        val rover = mars.welcomeIncomingRover(Pair(1, 1))
        rover.turnLeft()
        rover.moveForward()

        expectThat(rover.position).isEqualTo(Pair(0, 1))
    }

    @Test
    fun `it should be able to move east`() {
        val rover = mars.welcomeIncomingRover(Pair(1, 1))
        rover.turnRight()
        rover.moveForward()

        expectThat(rover.position).isEqualTo(Pair(2, 1))
    }

    @Test
    fun `it should be able to move south`() {
        val rover = mars.welcomeIncomingRover(Pair(1, 1))
        rover.turnRight()
        rover.turnRight()
        rover.moveForward()

        expectThat(rover.position).isEqualTo(Pair(1, 0))
    }

    @Test
    fun `it should indicate if they become non-operational (falling a cliff)`() {
        val rover = mars.welcomeIncomingRover(Pair(1, 1))
        rover.turnLeft()
        rover.moveForward()
        rover.moveForward()

        expectThat(rover.isOperational).isEqualTo(false)
    }

    @Test
    fun `it should no longer move if not operational`() {
        val rover = mars.welcomeIncomingRover(Pair(1, 1))
        rover.turnLeft()
        rover.moveForward()
        rover.moveForward()

        expectThat(rover.position).isEqualTo(Pair(0, 1))
        expectThat(rover.direction).isEqualTo(Direction.WEST)
        expectThat(rover.isOperational).isEqualTo(false)

        rover.turnRight()
        rover.moveForward()
        expectThat(rover.position).isEqualTo(Pair(0, 1))
        expectThat(rover.direction).isEqualTo(Direction.WEST)
        expectThat(rover.isOperational).isEqualTo(false)
    }
}
