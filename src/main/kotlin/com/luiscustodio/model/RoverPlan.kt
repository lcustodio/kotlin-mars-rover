package com.luiscustodio.model

data class RoverPlan(
    val initialLocation: Pair<Int, Int>,
    val direction: Direction = Direction.NORTH,
    val sequenceCommands: List<(Rover) -> Rover>,
)
