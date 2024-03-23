package com.luiscustodio.model

import com.luiscustodio.exception.FailureToLandInMarsException
import com.luiscustodio.exception.NoSuchBigPlanetExistsException
import com.luiscustodio.service.SpaceStation

class Mars(
    private val planetSize: Pair<Int, Int>,
    private val spaceStation: SpaceStation,
) {
    init {
        if (planetSize.first > 50 || planetSize.second > 50) {
            throw NoSuchBigPlanetExistsException("Planet dimensions must not exceed 50 in any direction.")
        }
    }

    fun welcomeIncomingRover(
        startingPoint: Pair<Int, Int>,
        direction: Direction = Direction.NORTH,
    ): Rover {
        if (startingPoint.first > planetSize.first || startingPoint.second > planetSize.second) {
            throw FailureToLandInMarsException("Starting point is outside the boundaries of Mars.")
        }
        return Rover(startingPoint, direction, spaceStation = spaceStation, planetSize = planetSize)
    }
}
