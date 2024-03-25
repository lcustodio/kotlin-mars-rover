package com.luiscustodio

import com.luiscustodio.exception.NoSuchBigPlanetExistsException
import com.luiscustodio.model.Mars
import strikt.api.expectCatching
import strikt.assertions.isA
import strikt.assertions.isFailure
import kotlin.test.Test

class MarsTest {
    @Test
    fun `mars coordinates should have the maximum value of 50 `() {
        expectCatching { Mars(Pair(1, 51)) }
            .isFailure()
            .isA<NoSuchBigPlanetExistsException>()
    }
}
