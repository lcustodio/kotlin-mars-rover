package com.luiscustodio.service

import com.luiscustodio.exception.FailureToLandInMarsException
import com.luiscustodio.model.Direction
import com.luiscustodio.model.Mars
import com.luiscustodio.model.Rover

class SpaceStation {
    private val crashPositions = mutableListOf<Pair<Int, Int>>()

    fun craftRoverForPlanet(
        landingPosition: Pair<Int, Int>,
        direction: Direction = Direction.NORTH,
        planet: Mars,
    ): Rover {
        if (landingPosition.first > planet.planetSize.first || landingPosition.second > planet.planetSize.second) {
            throw FailureToLandInMarsException("Invalid landing instruction. The position is outside the boundaries of Mars.")
        }
        return Rover(landingPosition, direction, spaceStation = this, planetSize = planet.planetSize)
    }

    fun reportCrashPosition(position: Pair<Int, Int>) {
        this.crashPositions.add(position)
    }

    fun isCrashReportedPosition(positionCheck: Pair<Int, Int>): Boolean {
        return this.crashPositions.any { position ->
            position == positionCheck
        }
    }
}
