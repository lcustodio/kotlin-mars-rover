package com.luiscustodio.model

import com.luiscustodio.exception.NoSuchBigPlanetExistsException

class Mars(val planetSize: Pair<Int, Int>) {
    init {
        if (planetSize.first > 50 || planetSize.second > 50) {
            throw NoSuchBigPlanetExistsException("Planet dimensions must not exceed 50 in any direction.")
        }
    }
}
