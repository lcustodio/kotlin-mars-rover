package com.luiscustodio.model

import com.luiscustodio.exception.FailureToLandInMarsException
import com.luiscustodio.exception.NoSuchBigPlanetExistsException

class Mars(internal val planetSize: Pair<Int, Int>) {
    internal val roversHistory = mutableListOf<Rover>()

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
        val rover = Rover(startingPoint, direction, this)
        roversHistory.add(rover)
        return rover
    }

    fun isThereRoverScent(newPosition: Pair<Int, Int>): Boolean {
        return roversHistory.any { rover ->
            !rover.isOperational && rover.position == newPosition
        }
    }
}
