package com.luiscustodio.model

enum class Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST,
    ;

    companion object {
        fun anticlockwise(currentEnum: Direction): Direction {
            val values = entries.toTypedArray()
            val previousIndex = (currentEnum.ordinal - 1 + values.size) % values.size
            return values[previousIndex]
        }

        fun clockwise(currentEnum: Direction): Direction {
            val values = entries.toTypedArray()
            val nextIndex = (currentEnum.ordinal + 1) % values.size
            return values[nextIndex]
        }
    }
}
