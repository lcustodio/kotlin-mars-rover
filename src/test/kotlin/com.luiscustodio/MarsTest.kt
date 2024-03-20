package com.luiscustodio

import com.luiscustodio.expection.FailureToLandInMarsException
import com.luiscustodio.expection.NoSuchBigPlanetExistsException
import com.luiscustodio.model.Mars
import strikt.api.expectCatching
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import strikt.assertions.isFailure
import kotlin.test.Test

class MarsTest {
    private val mars = Mars(Pair(2, 2))

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

    @Test
    fun `only one rover can be lost in a given grid point, future rover should ignore destructive instructions`() {
        val rover = mars.welcomeIncomingRover(Pair(1, 1))
        rover.turn('L')
        rover.moveForward()
        rover.moveForward()

        val rover2 = mars.welcomeIncomingRover(Pair(1, 1))
        rover2.turn('L')
        rover2.moveForward()
        rover2.moveForward()

        expectThat(rover2.isOperational).isEqualTo(true)
        expectThat(rover2.position).isEqualTo(Pair(1, 0))
    }
}