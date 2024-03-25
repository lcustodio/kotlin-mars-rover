package com.luiscustodio.model

import com.luiscustodio.service.SpaceStation

data class Rover(
    val position: Pair<Int, Int>,
    val direction: Direction = Direction.NORTH,
    val isOperational: Boolean = true,
    private val spaceStation: SpaceStation,
    private val planetSize: Pair<Int, Int>,
) {
    fun moveForward(): Rover {
        if (!isOperational) return this
        return assessBoundaries(
            when (direction) {
                Direction.NORTH -> Pair(position.first, position.second + 1)
                Direction.EAST -> Pair(position.first + 1, position.second)
                Direction.SOUTH -> Pair(position.first, position.second - 1)
                Direction.WEST -> Pair(position.first - 1, position.second)
            },
        )
    }

    private fun assessBoundaries(newPosition: Pair<Int, Int>): Rover {
        if (newPosition.first in 0..planetSize.first &&
            newPosition.second in 0..planetSize.second
        ) {
            return this.copy(position = newPosition)
        }

        val shouldIgnoreInstructions = spaceStation.isCrashReportedPosition(position)
        if (shouldIgnoreInstructions) {
            return this
        }
//        spaceStation.reportCrashPosition(position)
        return this.copy(isOperational = false)
    }

    fun turnLeft(): Rover {
        if (!isOperational) return this
        return this.copy(direction = Direction.anticlockwise(direction))
    }

    fun turnRight(): Rover {
        if (!isOperational) return this
        return this.copy(direction = Direction.clockwise(direction))
    }
}
