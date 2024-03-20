package com.luiscustodio.util

import com.luiscustodio.model.Direction

fun toChar(direction: Direction): Char {
    return when (direction) {
        Direction.NORTH -> 'N'
        Direction.EAST -> 'E'
        Direction.SOUTH -> 'S'
        else -> 'W'
    }
}

fun from(c: Char): Direction {
    return when (c) {
        'N' -> Direction.NORTH
        'E' -> Direction.EAST
        'S' -> Direction.SOUTH
        else -> Direction.WEST
    }
}
