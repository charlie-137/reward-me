package com.brogrammer.rewardme.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.brogrammer.rewardme.data.model.Customer

@Dao
interface CustomerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customer: Customer)

    @Update
    suspend fun updateCustomer(customer: Customer)

    @Delete
    suspend fun deleteCustomer(customer: Customer)

    @Query("SELECT * FROM customers")
    fun getAllCustomers(): LiveData<List<Customer>>

    @Query("SELECT * FROM customers WHERE id = :customerId")
    fun getCustomerById(customerId: Int): LiveData<Customer>

    @Query("UPDATE customers SET points = points + :points Where id = :customerId")
    suspend fun updatePoints(customerId: Int, points: Int)

    @Query("SELECT points FROM customers WHERE id = :customerId")
    fun getPointsByCustomerId(customerId: Int): LiveData<Int>

    @Query("SELECT * FROM customers")
    suspend fun getAllCustomersList(): List<Customer>

}