package com.luiscustodio

import com.luiscustodio.adapter.charToDirection
import com.luiscustodio.adapter.positionsStringOutput
import com.luiscustodio.adapter.processCommands
import com.luiscustodio.model.Mars
import com.luiscustodio.model.RoverPlan
import com.luiscustodio.service.SpaceStation

class MarsRoverCommunicationChannel {
    private val spaceStation = SpaceStation()

    fun textHandler(instructions: String): String {
        val lines = instructions.split("\n").filter { it.isNotBlank() }
        val (width, height) = getPlanetSize(lines)

        val roverPlans =
            parseRoverDefinitionAndInstructions(lines).map { chunk ->
                val (x, y, directionChar) = chunk[0].split(" ")
                RoverPlan(
                    initialLocation = Pair(x.toInt(), y.toInt()),
                    direction = charToDirection(directionChar[0]),
                    sequenceCommands =
                        processCommands(
                            commands = chunk[1],
                        ),
                )
            }

        val projectResult = signalSpaceStationToDeployRovers(planetSize = Pair(width, height), roverPlans = roverPlans)
        return projectResult.joinToString(separator = "\n\n")
    }

    private fun signalSpaceStationToDeployRovers(
        planetSize: Pair<Int, Int>,
        roverPlans: List<RoverPlan>,
    ): List<String> {
        val mars = Mars(planetSize = planetSize)
        val rovers = spaceStation.deployRoversToPlanet(roverPlans, mars)
        return rovers.map { it.positionsStringOutput() }
    }

    private fun parseRoverDefinitionAndInstructions(lines: List<String>) = lines.drop(1).chunked(2)

    private fun getPlanetSize(lines: List<String>) = lines.first().split(" ").map { it.toInt() }
}
