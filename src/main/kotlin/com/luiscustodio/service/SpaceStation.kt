package com.luiscustodio.service

import com.luiscustodio.exception.FailureToLandInMarsException
import com.luiscustodio.model.Mars
import com.luiscustodio.model.Rover
import com.luiscustodio.model.RoverPlan

class SpaceStation {
    private val rovers = mutableListOf<Rover>()

    fun deployRoversToPlanet(
        roverPlans: List<RoverPlan>,
        planet: Mars,
    ): List<Rover> {
        roverPlans.forEach {
            if (it.initialLocation.first > planet.planetSize.first || it.initialLocation.second > planet.planetSize.second) {
                throw FailureToLandInMarsException("Invalid landing instruction. The position is outside the boundaries of Mars.")
            }

            val baseRover = Rover(it.initialLocation, it.direction, spaceStation = this, planetSize = planet.planetSize)
            val rover =
                it.sequenceCommands.fold(baseRover) { rover, command ->
                    command(rover)
                }
            rovers.add(rover)
        }
        return rovers
    }

    fun isCrashReportedPosition(positionCheck: Pair<Int, Int>): Boolean {
        return rovers.any { rover ->
            !rover.isOperational && rover.position == positionCheck
        }
    }
}
