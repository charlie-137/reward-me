package com.brogrammer.rewardme.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.brogrammer.rewardme.data.database.AppDatabase
import com.brogrammer.rewardme.data.model.ConversionRate
import com.brogrammer.rewardme.data.repository.ConversionRateRepository
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ConversionRateRepository
    val conversionRate: LiveData<ConversionRate>

    init {
        val conversionRateDao = AppDatabase.getDatabase(application).conversionRateDao()
        repository = ConversionRateRepository(conversionRateDao)
        conversionRate = repository.conversionRate
    }

    fun insert(conversionRate: ConversionRate) = viewModelScope.launch {
        repository.insert(conversionRate)
    }

    fun update(conversionRate: ConversionRate) = viewModelScope.launch {
        repository.update(conversionRate)
    }
}