package com.luiscustodio.util

import com.luiscustodio.model.Rover

fun Rover.processCommands(commands: String) {
    commands.forEach { command ->
        when (command) {
            'R' -> turnRight()
            'L' -> turnLeft()
            'F' -> moveForward()
        }
    }
}
