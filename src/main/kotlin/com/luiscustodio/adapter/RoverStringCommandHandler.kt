package com.luiscustodio.adapter

import com.luiscustodio.model.Rover

fun Rover.processCommands(commands: String): Rover {
    return commands.asSequence().fold(this) { rover, command ->
        when (command) {
            'R' -> rover.turnRight()
            'L' -> rover.turnLeft()
            else -> rover.moveForward()
        }
    }
}

fun Rover.positionsStringOutput(): String {
    return "${position.first} ${position.second} ${directionToChar(direction)} ${if (!isOperational) "LOST" else ""}"
}
