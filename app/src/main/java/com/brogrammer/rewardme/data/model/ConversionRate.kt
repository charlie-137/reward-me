package com.brogrammer.rewardme.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversion_rate")
data class ConversionRate(
    @PrimaryKey
    val id: Int = 1,
    val moneyToPointsRate: Float,
    val pointsToMoneyRate: Float
)