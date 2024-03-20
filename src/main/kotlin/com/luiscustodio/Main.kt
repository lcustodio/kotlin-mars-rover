package com.luiscustodio

import com.luiscustodio.model.Mars
import com.luiscustodio.model.Rover
import com.luiscustodio.util.allRoverPositions
import com.luiscustodio.util.from

fun handleInstructions(instructions: String): String {
    val lines = instructions.split("\n").filter { it.isNotBlank() }

    val (width, height) = lines.first().split(" ").map { it.toInt() }
    val mars = Mars(Pair(width, height))

    lines.drop(1).chunked(2).forEach { chunk ->
        val (x, y, directionChar) = chunk[0].split(" ")
        val direction = from(directionChar[0])
        val commands = chunk[1]

        val rover = mars.welcomeIncomingRover(Pair(x.toInt(), y.toInt()), direction)
        rover.processCommands(commands)
    }

    return mars.allRoverPositions()
}

fun Rover.processCommands(commands: String) {
    commands.forEach { command ->
        when (command) {
            'R' -> turn('R')
            'L' -> turn('L')
            'F' -> moveForward()
        }
    }
}
