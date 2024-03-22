package com.luiscustodio.adapter

import com.luiscustodio.model.Direction

fun directionToChar(direction: Direction): Char {
    return when (direction) {
        Direction.NORTH -> 'N'
        Direction.EAST -> 'E'
        Direction.SOUTH -> 'S'
        Direction.WEST -> 'W'
    }
}

fun charToDirection(c: Char): Direction {
    return when (c) {
        'N' -> Direction.NORTH
        'E' -> Direction.EAST
        'S' -> Direction.SOUTH
        else -> Direction.WEST
    }
}
