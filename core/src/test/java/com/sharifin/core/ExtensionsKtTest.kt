package com.sharifin.core

import org.junit.Assert.*
import org.junit.Test

class ExtensionsKtTest{


    @Test
    fun `converts standard to persian correctly`() {
        val standardTime = "2019-07-03T07:26:07.395642Z"
        val persianTime = standardTime.standardDateToPersianDateOnly(true)
        assertEquals(persianTime, "12 تیر 1398")
    }

}