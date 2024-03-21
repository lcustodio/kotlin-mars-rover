package com.luiscustodio.model

class Rover(
    var position: Pair<Int, Int>,
    var direction: Direction,
    private val mars: Mars,
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
        if (newPosition.first < 0 || newPosition.first > mars.planetSize.first ||
            newPosition.second < 0 || newPosition.second > mars.planetSize.second
        ) {
            if (mars.isThereRoverScent(position)) {
                return position
            }
            isOperational = false
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
