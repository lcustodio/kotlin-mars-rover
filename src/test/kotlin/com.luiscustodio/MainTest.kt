package com.luiscustodio

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

class PlaceholderFunctionTest {
    @Test
    fun `it should describe a happy path`() {
        expectThat(placeholderFunction()).isEqualTo(1)
    }
}
