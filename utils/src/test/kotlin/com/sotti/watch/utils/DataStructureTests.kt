package com.sotti.watch.utils

import org.junit.Assert
import org.junit.Test

internal class DataStructureTests {

    @Test
    fun mapToSetTests() {
        Assert.assertEquals(listOf(1, 1, 2, 2, 3, 3).mapToSet { it * 2 }, setOf(2, 4, 6))
    }
}