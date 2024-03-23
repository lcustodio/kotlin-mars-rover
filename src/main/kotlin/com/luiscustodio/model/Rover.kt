package com.luiscustodio.model

import com.luiscustodio.service.SpaceStation

data class Rover(
    val position: Pair<Int, Int>,
    val direction: Direction,
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
        if (newPosition.first < 0 || newPosition.first > planetSize.first ||
            newPosition.second < 0 || newPosition.second > planetSize.second
        ) {
            if (spaceStation.isCrashReportedPosition(position)) {
                return this.copy(position = position)
            }

            spaceStation.reportCrashPosition(position)
            return this.copy(position = position, isOperational = false)
        }
        return this.copy(position = newPosition)
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
