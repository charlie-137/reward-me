package com.brogrammer.rewardme.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.brogrammer.rewardme.data.model.ConversionRate
import com.brogrammer.rewardme.data.model.Transaction

@Dao
interface ConversionRateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConversionRate(conversionRate: ConversionRate)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConversionRates(conversionRates: List<ConversionRate>)

    @Query("SELECT * FROM conversion_rate")
    fun getAllConversionRate(): LiveData<List<ConversionRate>>

    @Update
    suspend fun updateConversionRate(conversionRate: ConversionRate)

    @Query("SELECT * FROM conversion_rate WHERE id = 1")
    fun getConversionRate(): LiveData<ConversionRate>

    @Query("SELECT * FROM conversion_rate")
    suspend fun getAllConversionRateList(): List<ConversionRate>

}