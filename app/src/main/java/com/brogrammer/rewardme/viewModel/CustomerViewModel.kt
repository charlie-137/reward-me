package com.brogrammer.rewardme.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.brogrammer.rewardme.data.database.AppDatabase
import com.brogrammer.rewardme.data.model.Customer
import com.brogrammer.rewardme.data.repository.CustomerRepository
import kotlinx.coroutines.launch

class CustomerViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CustomerRepository
    val allCustomers: LiveData<List<Customer>>

    init {
        val customerDao = AppDatabase.getDatabase(application).customerDao()
        repository = CustomerRepository(customerDao)
        allCustomers = repository.allCustomers
    }

    fun insert(customer: Customer) = viewModelScope.launch {
        repository.insert(customer)
    }

    fun update(customer: Customer) = viewModelScope.launch {
        repository.update(customer)
    }

    fun getCustomerById(customerId: Int): LiveData<Customer> {
        return repository.getCustomerById(customerId)
    }

//    fun updatePoints(customerId: Int, points: Int) = viewModelScope.launch {
//        val customer = repository.getCustomerById(customerId).value
//        customer?.let {
//            val updatedCustomer = it.copy(points = it.points + points)
//            repository.update(updatedCustomer)
//        }
//    }

    fun updatePoints(customerId: Int, points: Int) = viewModelScope.launch {
        repository.updatePoints(customerId, points)
    }



}