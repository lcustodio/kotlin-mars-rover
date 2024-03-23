package com.luiscustodio

import com.luiscustodio.exception.FailureToLandInMarsException
import com.luiscustodio.exception.NoSuchBigPlanetExistsException
import com.luiscustodio.model.Mars
import com.luiscustodio.service.SpaceStation
import strikt.api.expectCatching
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import strikt.assertions.isFailure
import kotlin.test.Test

class MarsTest {
    private val spaceStation = SpaceStation()
    private val mars = Mars(Pair(2, 2))

    @Test
    fun `rover should land in a valid planet location`() {
        expectCatching { spaceStation.craftRoverForPlanet(landingPosition = Pair(5, 5), planet = mars) }
            .isFailure()
            .isA<FailureToLandInMarsException>()
    }

    @Test
    fun `mars coordinates should have the maximum value of 50 `() {
        expectCatching { Mars(Pair(1, 51)) }
            .isFailure()
            .isA<NoSuchBigPlanetExistsException>()
    }

    @Test
    fun `only one rover can be lost in a given grid point, future rover should ignore destructive instructions`() {
        spaceStation.craftRoverForPlanet(landingPosition = Pair(1, 1), planet = mars)
            .turnLeft()
            .moveForward()
            .moveForward()

        val rover2 =
            spaceStation.craftRoverForPlanet(landingPosition = Pair(1, 1), planet = mars)
                .turnLeft()
                .moveForward()
                .moveForward()

        expectThat(rover2.isOperational).isEqualTo(true)
        expectThat(rover2.position).isEqualTo(Pair(0, 1))
    }
}
