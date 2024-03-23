package com.luiscustodio.adapter

import com.luiscustodio.model.Rover

fun Rover.processCommands(commands: String): Rover {
    commands.forEach { command ->
        when (command) {
            'R' -> turnRight()
            'L' -> turnLeft()
            'F' -> moveForward()
        }
    }
    return this
}
