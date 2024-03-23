package com.luiscustodio

import com.luiscustodio.adapter.charToDirection
import com.luiscustodio.adapter.positionsStringOutput
import com.luiscustodio.adapter.processCommands
import com.luiscustodio.model.Mars
import com.luiscustodio.service.SpaceStation

fun handleInstructions(instructions: String): String {
    val lines = instructions.split("\n").filter { it.isNotBlank() }

    val (width, height) = lines.first().split(" ").map { it.toInt() }
    val spaceStation = SpaceStation()
    val mars = Mars(planetSize = Pair(width, height), spaceStation = spaceStation)

    return lines.drop(1).chunked(2).joinToString(separator = "\n\n") { chunk ->
        val (x, y, directionChar) = chunk[0].split(" ")
        val direction = charToDirection(directionChar[0])
        val commands = chunk[1]

        val rover = mars.welcomeIncomingRover(Pair(x.toInt(), y.toInt()), direction)
        rover.processCommands(commands).positionsStringOutput()
    }
}
