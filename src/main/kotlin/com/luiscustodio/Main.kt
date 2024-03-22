package com.luiscustodio

import com.luiscustodio.adapter.charToDirection
import com.luiscustodio.adapter.processCommands
import com.luiscustodio.adapter.roversPositionsStringOutput
import com.luiscustodio.model.Mars

fun handleInstructions(instructions: String): String {
    val lines = instructions.split("\n").filter { it.isNotBlank() }

    val (width, height) = lines.first().split(" ").map { it.toInt() }
    val mars = Mars(Pair(width, height))

    lines.drop(1).chunked(2).forEach { chunk ->
        val (x, y, directionChar) = chunk[0].split(" ")
        val direction = charToDirection(directionChar[0])
        val commands = chunk[1]

        val rover = mars.welcomeIncomingRover(Pair(x.toInt(), y.toInt()), direction)
        rover.processCommands(commands)
    }

    return mars.roversPositionsStringOutput()
}
