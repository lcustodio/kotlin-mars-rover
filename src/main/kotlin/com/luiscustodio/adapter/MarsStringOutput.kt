package com.luiscustodio.adapter

import com.luiscustodio.model.Rover

fun Rover.positionsStringOutput(): String {
    return "${position.first} ${position.second} ${directionToChar(direction)} ${if (!isOperational) "LOST" else ""}"
}
