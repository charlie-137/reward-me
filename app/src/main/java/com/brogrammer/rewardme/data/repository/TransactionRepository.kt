package com.brogrammer.rewardme.data.repository

import androidx.lifecycle.LiveData
import com.brogrammer.rewardme.data.dao.TransactionDao
import com.brogrammer.rewardme.data.model.Transaction

class TransactionRepository(private val transactionDao: TransactionDao) {
    fun getTransactionsByCustomerId(customerId: Int): LiveData<List<Transaction>> {
        return transactionDao.getTransactionsByCustomerId(customerId)
    }

    suspend fun insert(transaction: Transaction) {
        transactionDao.insertTransaction(transaction)
    }

    suspend fun update(transaction: Transaction) {
        transactionDao.updateTransaction(transaction)
    }

    suspend fun delete(transaction: Transaction) {
        transactionDao.deleteTransaction(transaction)
    }
}