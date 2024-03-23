package com.luiscustodio

import com.luiscustodio.adapter.charToDirection
import com.luiscustodio.adapter.positionsStringOutput
import com.luiscustodio.adapter.processCommands
import com.luiscustodio.model.Mars
import com.luiscustodio.service.SpaceStation

class MarsHoverCommunicationChannel {
    fun textHandler(instructions: String): String {
        val lines = instructions.split("\n").filter { it.isNotBlank() }
        val (width, height) = getPlanetSize(lines)

        val spaceStation = SpaceStation()
        val mars = Mars(planetSize = Pair(width, height))

        return getRoverDefinitionAndInstructions(lines).joinToString(separator = "\n\n") { chunk ->
            val (x, y, directionChar) = chunk[0].split(" ")
            val direction = charToDirection(directionChar[0])
            val commands = chunk[1]

            val rover =
                spaceStation.craftRoverForPlanet(
                    landingPosition = Pair(x.toInt(), y.toInt()),
                    direction = direction,
                    planet = mars,
                )

            rover.processCommands(commands).positionsStringOutput()
        }
    }

    private fun getRoverDefinitionAndInstructions(lines: List<String>) = lines.drop(1).chunked(2)

    private fun getPlanetSize(lines: List<String>) = lines.first().split(" ").map { it.toInt() }
}
