package com.brogrammer.rewardme.data.repository

import androidx.lifecycle.LiveData
import com.brogrammer.rewardme.data.dao.ConversionRateDao
import com.brogrammer.rewardme.data.model.ConversionRate

class ConversionRateRepository(private val conversionRateDao: ConversionRateDao) {
    val conversionRate: LiveData<ConversionRate> = conversionRateDao.getConversionRate()

    suspend fun insert(conversionRate: ConversionRate){
        conversionRateDao.insertConversionRate(conversionRate)
    }

    suspend fun update(conversionRate: ConversionRate){
        conversionRateDao.updateConversionRate(conversionRate)
    }
}