package com.brogrammer.rewardme.data.repository

import androidx.lifecycle.LiveData
import com.brogrammer.rewardme.data.dao.CustomerDao
import com.brogrammer.rewardme.data.model.Customer

class CustomerRepository(private val customerDao: CustomerDao) {
    val allCustomers: LiveData<List<Customer>> = customerDao.getAllCustomers()

    suspend fun insert(customer: Customer) {
        customerDao.insertCustomer(customer)
    }

    suspend fun update(customer: Customer) {
        customerDao.updateCustomer(customer)
    }

    suspend fun delete(customer: Customer) {
        customerDao.deleteCustomer(customer)
    }

    fun getCustomerById(customerId: Int): LiveData<Customer> {
        return customerDao.getCustomerById(customerId)
    }

    suspend fun updatePoints(customerId: Int, points: Int) {
        customerDao.updatePoints(customerId, points)
    }

    fun getPointsByCustomerId(customerId: Int): LiveData<Int> {
        return customerDao.getPointsByCustomerId(customerId)
    }

}