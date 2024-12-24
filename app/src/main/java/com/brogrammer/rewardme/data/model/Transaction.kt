package com.brogrammer.rewardme.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val customerId: Int,
    val date: Date,
    val type: String, // "Add" or "Redeem"
    val points: Int,
    val description: String
)
