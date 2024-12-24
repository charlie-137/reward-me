package com.brogrammer.rewardme.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.brogrammer.rewardme.data.dao.ConversionRateDao
import com.brogrammer.rewardme.data.dao.CustomerDao
import com.brogrammer.rewardme.data.dao.TransactionDao
import com.brogrammer.rewardme.data.model.ConversionRate
import com.brogrammer.rewardme.data.model.Customer
import com.brogrammer.rewardme.data.model.Transaction

@Database(entities = [Customer::class, Transaction::class, ConversionRate::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao
    abstract fun transactionDao(): TransactionDao
    abstract fun conversionRateDao(): ConversionRateDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}