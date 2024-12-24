package com.brogrammer.rewardme.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.brogrammer.rewardme.data.model.ConversionRate

@Dao
interface ConversionRateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConversionRate(conversionRate: ConversionRate)

    @Update
    suspend fun updateConversionRate(conversionRate: ConversionRate)

    @Query("SELECT * FROM conversion_rate WHERE id = 1")
    fun getConversionRate(): LiveData<ConversionRate>
}