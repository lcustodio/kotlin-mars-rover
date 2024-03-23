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
        val actualPosition =
            mars.welcomeIncomingRover(Pair(1, 1))
                .moveForward()
                .position

        expectThat(actualPosition).isEqualTo(Pair(1, 2))
    }

    @Test
    fun `it should be able to move forwards twice in a row`() {
        val mars = Mars(Pair(3, 3), spaceStation)
        val actualPosition =
            mars.welcomeIncomingRover(Pair(1, 1))
                .moveForward()
                .moveForward()
                .position

        expectThat(actualPosition).isEqualTo(Pair(1, 3))
    }

    @Test
    fun `it should be able to turn left and forward`() {
        val actualPosition =
            mars.welcomeIncomingRover(Pair(1, 1))
                .turnLeft()
                .moveForward()
                .position

        expectThat(actualPosition).isEqualTo(Pair(0, 1))
    }

    @Test
    fun `it should be able to move east`() {
        val actualPosition =
            mars.welcomeIncomingRover(Pair(1, 1))
                .turnRight()
                .moveForward()
                .position

        expectThat(actualPosition).isEqualTo(Pair(2, 1))
    }

    @Test
    fun `it should be able to move south`() {
        val actualPosition =
            mars.welcomeIncomingRover(Pair(1, 1))
                .turnRight()
                .turnRight()
                .moveForward()
                .position

        expectThat(actualPosition).isEqualTo(Pair(1, 0))
    }

    @Test
    fun `it should indicate if they become non-operational (falling a cliff)`() {
        val actualOperationalState =
            mars.welcomeIncomingRover(Pair(1, 1))
                .turnLeft()
                .moveForward()
                .moveForward()
                .isOperational

        expectThat(actualOperationalState).isEqualTo(false)
    }

    @Test
    fun `it should no longer move if not operational`() {
        val rover1 =
            mars.welcomeIncomingRover(Pair(1, 1))
                .turnLeft()
                .moveForward()
                .moveForward()

        expectThat(rover1.position).isEqualTo(Pair(0, 1))
        expectThat(rover1.direction).isEqualTo(Direction.WEST)
        expectThat(rover1.isOperational).isEqualTo(false)

        val keepsMoving =
            rover1
                .turnRight()
                .moveForward()

        expectThat(keepsMoving.position).isEqualTo(Pair(0, 1))
        expectThat(keepsMoving.direction).isEqualTo(Direction.WEST)
        expectThat(keepsMoving.isOperational).isEqualTo(false)
    }
}
