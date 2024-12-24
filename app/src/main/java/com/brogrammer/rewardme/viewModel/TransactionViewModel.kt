package com.brogrammer.rewardme.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.brogrammer.rewardme.data.database.AppDatabase
import com.brogrammer.rewardme.data.model.Transaction
import com.brogrammer.rewardme.data.repository.TransactionRepository
import kotlinx.coroutines.launch

class TransactionViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TransactionRepository

    init {
        val transactionDao = AppDatabase.getDatabase(application). transactionDao()
        repository = TransactionRepository((transactionDao))
    }

    fun getTransactionsByCustomerId(customerId: Int): LiveData<List<Transaction>> {
        return repository.getTransactionsByCustomerId(customerId)
    }

    fun insert(transaction: Transaction) = viewModelScope.launch {
        repository.insert(transaction)
    }

    fun update(transaction: Transaction) = viewModelScope.launch {
        repository.update(transaction)
    }

    fun delete(transaction: Transaction) = viewModelScope.launch {
        repository.delete(transaction)
    }
}