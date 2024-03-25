package com.luiscustodio.adapter

import com.luiscustodio.model.Rover

fun processCommands(commands: String): List<(Rover) -> Rover> {
    return commands.map { command ->
        when (command) {
            'R' -> { rover: Rover -> rover.turnRight() }
            'L' -> { rover: Rover -> rover.turnLeft() }
            else -> { rover: Rover -> rover.moveForward() }
        }
    }
}

fun Rover.positionsStringOutput(): String {
    return "${position.first} ${position.second} ${directionToChar(direction)} ${if (!isOperational) "LOST" else ""}"
}
