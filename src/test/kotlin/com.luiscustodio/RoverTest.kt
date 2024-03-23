package com.luiscustodio

import com.luiscustodio.model.Direction
import com.luiscustodio.model.Mars
import com.luiscustodio.service.SpaceStation
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

class RoverTest {
    private val spaceStation = SpaceStation()
    private val mars = Mars(Pair(2, 2))

    @Test
    fun `it should be able to move forwards`() {
        val actualPosition =
            spaceStation.craftRoverForPlanet(landingPosition = Pair(1, 1), planet = mars)
                .moveForward()
                .position

        expectThat(actualPosition).isEqualTo(Pair(1, 2))
    }

    @Test
    fun `it should be able to move forwards twice in a row`() {
        val mars = Mars(Pair(3, 3))
        val actualPosition =
            spaceStation.craftRoverForPlanet(landingPosition = Pair(1, 1), planet = mars)
                .moveForward()
                .moveForward()
                .position

        expectThat(actualPosition).isEqualTo(Pair(1, 3))
    }

    @Test
    fun `it should be able to turn left and forward`() {
        val actualPosition =
            spaceStation.craftRoverForPlanet(landingPosition = Pair(1, 1), planet = mars)
                .turnLeft()
                .moveForward()
                .position

        expectThat(actualPosition).isEqualTo(Pair(0, 1))
    }

    @Test
    fun `it should be able to move east`() {
        val actualPosition =
            spaceStation.craftRoverForPlanet(landingPosition = Pair(1, 1), planet = mars)
                .turnRight()
                .moveForward()
                .position

        expectThat(actualPosition).isEqualTo(Pair(2, 1))
    }

    @Test
    fun `it should be able to move south`() {
        val actualPosition =
            spaceStation.craftRoverForPlanet(landingPosition = Pair(1, 1), planet = mars)
                .turnRight()
                .turnRight()
                .moveForward()
                .position

        expectThat(actualPosition).isEqualTo(Pair(1, 0))
    }

    @Test
    fun `it should indicate if they become non-operational (falling a cliff)`() {
        val actualOperationalState =
            spaceStation.craftRoverForPlanet(landingPosition = Pair(1, 1), planet = mars)
                .turnLeft()
                .moveForward()
                .moveForward()
                .isOperational

        expectThat(actualOperationalState).isEqualTo(false)
    }

    @Test
    fun `it should no longer move if not operational`() {
        val rover1 =
            spaceStation.craftRoverForPlanet(landingPosition = Pair(1, 1), planet = mars)
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
