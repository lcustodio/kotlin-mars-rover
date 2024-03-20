package com.luiscustodio.model

import com.luiscustodio.expection.FailureToLandInMarsException
import com.luiscustodio.expection.NoSuchBigPlanetExistsException

class Mars(internal val planetSize: Pair<Int, Int>) {
    private val roversHistory = mutableListOf<Rover>()

    init {
        if (planetSize.first > 50 || planetSize.second > 50) {
            throw NoSuchBigPlanetExistsException("Planet dimensions must not exceed 50 in any direction.")
        }
    }

    fun welcomeIncomingRover(startingPoint: Pair<Int, Int>): Rover {
        if (startingPoint.first > planetSize.first || startingPoint.second > planetSize.second) {
            throw FailureToLandInMarsException("Starting point is outside the boundaries of Mars.")
        }
        val rover = Rover(startingPoint, this)
        roversHistory.add(rover)
        return rover
    }

    fun isThereRoverScent(newPosition: Pair<Int, Int>): Boolean {
        return roversHistory.any { rover ->
            !rover.isOperational && rover.position == newPosition
        }
    }
}
