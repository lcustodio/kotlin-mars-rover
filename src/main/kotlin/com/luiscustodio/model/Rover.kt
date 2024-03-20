package com.luiscustodio.model

class Rover(startingPosition: Pair<Int, Int>, private val mars: Mars) {
    var isOperational = true
    var position = startingPosition
    private var direction: Direction = Direction.NORTH

    fun moveForward() {
        position =
            assessBoundaries(
                when (direction) {
                    Direction.NORTH -> Pair(position.first + 1, position.second)
                    Direction.EAST -> Pair(position.first, position.second + 1)
                    Direction.SOUTH -> Pair(position.first - 1, position.second)
                    Direction.WEST -> Pair(position.first, position.second - 1)
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

    fun turn(c: Char) {
        direction =
            when (c) {
                'L' -> Direction.anticlockwise(direction)
                else -> Direction.clockwise(direction)
            }
    }
}
