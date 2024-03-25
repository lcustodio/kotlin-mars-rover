package com.luiscustodio

import com.luiscustodio.exception.FailureToLandInMarsException
import com.luiscustodio.model.Mars
import com.luiscustodio.model.Rover
import com.luiscustodio.model.RoverPlan
import com.luiscustodio.service.SpaceStation
import strikt.api.expectCatching
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import strikt.assertions.isFailure
import kotlin.test.Test

class SpaceStationTest {
    private val spaceStation = SpaceStation()
    private val mars = Mars(Pair(2, 2))

    @Test
    fun `rover should land in a valid planet location`() {
        val sequenceCommands =
            listOf<(Rover) -> Rover> { rover ->
                rover.moveForward()
            }

        val plans =
            listOf(
                RoverPlan(
                    initialLocation = Pair(5, 5),
                    sequenceCommands = sequenceCommands,
                ),
            )

        expectCatching { spaceStation.deployRoversToPlanet(roverPlans = plans, planet = mars) }
            .isFailure()
            .isA<FailureToLandInMarsException>()
    }

    @Test
    fun `only one rover can be lost in a given grid point, future rover should ignore destructive instructions`() {
        val plans =
            listOf(
                RoverPlan(
                    initialLocation = Pair(1, 1),
                    sequenceCommands =
                        listOf(
                            { rover -> rover.turnLeft() },
                            { rover -> rover.moveForward() },
                            { rover -> rover.moveForward() },
                        ),
                ),
                RoverPlan(
                    initialLocation = Pair(1, 1),
                    sequenceCommands =
                        listOf(
                            { rover -> rover.turnLeft() },
                            { rover -> rover.moveForward() },
                            { rover -> rover.moveForward() },
                        ),
                ),
            )

        val rovers = spaceStation.deployRoversToPlanet(roverPlans = plans, planet = mars)

        expectThat(rovers[0].isOperational).isEqualTo(false)
        expectThat(rovers[0].position).isEqualTo(Pair(0, 1))

        expectThat(rovers[1].isOperational).isEqualTo(true)
        expectThat(rovers[1].position).isEqualTo(Pair(0, 1))
    }
}
