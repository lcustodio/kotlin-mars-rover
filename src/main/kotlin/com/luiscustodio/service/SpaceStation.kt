package com.luiscustodio.service

class SpaceStation {
    private val crashPositions = mutableListOf<Pair<Int, Int>>()

    fun reportCrashPosition(position: Pair<Int, Int>) {
        this.crashPositions.add(position)
    }

    fun isCrashReportedPosition(positionCheck: Pair<Int, Int>): Boolean {
        return this.crashPositions.any { position ->
            position == positionCheck
        }
    }
}
