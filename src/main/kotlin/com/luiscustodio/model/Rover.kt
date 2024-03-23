package com.luiscustodio.model

import com.luiscustodio.service.SpaceStation

class Rover(
    var position: Pair<Int, Int>,
    var direction: Direction,
    private val spaceStation: SpaceStation,
    private val planetSize: Pair<Int, Int>,
) {
    var isOperational = true

    fun moveForward() {
        if (!isOperational) return
        position =
            assessBoundaries(
                when (direction) {
                    Direction.NORTH -> Pair(position.first, position.second + 1)
                    Direction.EAST -> Pair(position.first + 1, position.second)
                    Direction.SOUTH -> Pair(position.first, position.second - 1)
                    Direction.WEST -> Pair(position.first - 1, position.second)
                },
            )
    }

    private fun assessBoundaries(newPosition: Pair<Int, Int>): Pair<Int, Int> {
        if (newPosition.first < 0 || newPosition.first > planetSize.first ||
            newPosition.second < 0 || newPosition.second > planetSize.second
        ) {
            if (spaceStation.isCrashReportedPosition(position)) {
                return position
            }
            isOperational = false
            spaceStation.reportCrashPosition(position)
            return position
        }
        return newPosition
    }

    fun turnLeft() {
        if (!isOperational) return
        direction = Direction.anticlockwise(direction)
    }

    fun turnRight() {
        if (!isOperational) return
        direction = Direction.clockwise(direction)
    }
}
