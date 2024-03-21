package com.luiscustodio.util

import com.luiscustodio.model.Mars

fun Mars.roversPositionsStringOutput(): String {
    val output =
        roversHistory.map {
            "${it.position.first} ${it.position.second} ${directionToChar(it.direction)} ${if (!it.isOperational) "LOST" else ""}"
        }
    return output.joinToString(separator = "\n\n")
}
