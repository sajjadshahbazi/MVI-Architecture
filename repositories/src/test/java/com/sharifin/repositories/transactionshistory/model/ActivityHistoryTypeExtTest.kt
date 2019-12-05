package com.sharifin.repositories.transactionshistory.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ActivityHistoryTypeExtTest : ActivityHistoryTypeExt{


    @Test
    fun `to types and back works correctly`(){
        val types = arrayOf(
                ActivityHistoryType.MobileCharge,
                ActivityHistoryType.CardToCard
        )

        assertThat(types)
                .isEqualTo(
                        types.commaSeparated()
                                .toTypes())
    }

    @Test
    fun `converts and retrieves All Type correctly`(){
        val types = arrayOf(
                ActivityHistoryType.All
        )

        assertThat(types)
                .isEqualTo(types.commaSeparated().toTypes())

    }

    @Test
    fun `converts All Type correctly`(){
        val types = arrayOf(
                ActivityHistoryType.All
        )

        assertThat(types.commaSeparated())
                .isEqualTo("")

    }
}