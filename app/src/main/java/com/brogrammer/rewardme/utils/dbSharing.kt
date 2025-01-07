////package com.brogrammer.rewardme.utils
////
////import android.content.Context
////import android.content.Intent
////import android.net.Uri
////import androidx.lifecycle.LiveData
////import androidx.lifecycle.Observer
////import com.brogrammer.rewardme.data.database.AppDatabase
////import com.brogrammer.rewardme.data.model.ConversionRate
////import com.brogrammer.rewardme.data.model.Customer
////import com.brogrammer.rewardme.data.model.Transaction
////import com.google.gson.Gson
////import com.google.gson.GsonBuilder
////import java.io.File
////import java.io.FileWriter
////import java.io.IOException
////import java.util.Date
////
////fun exportDatabaseToJson(context: Context) {
////
////    // Create a Gson instance with the custom DateTypeAdapter
//////    val gson = GsonBuilder()
//////        .registerTypeAdapter(Date::class.java, DateTypeAdapter())
//////        .setPrettyPrinting()
//////        .create()
////
////    // Get all data from the Room DB
////    val customerDao = AppDatabase.getDatabase(context).customerDao()
////    val transactionDao = AppDatabase.getDatabase(context).transactionDao()
////    val conversionRateDao = AppDatabase.getDatabase(context).conversionRateDao()
////
////    val customers: LiveData<List<Customer>> = customerDao.getAllCustomers()
////    val transactions: LiveData<List<Transaction>> = transactionDao.getAllTransactions()
////    val conversionRate: LiveData<List<ConversionRate>> = conversionRateDao.getAllConversionRate()
////
////    val gson = Gson()
////
////    val dbData = mutableMapOf<String, Any>()
////
////    customers.observeForever(object : Observer<List<Customer>> {
////        override fun onChanged(customerList: List<Customer>?) {
////            if (customerList != null) {
////                dbData["customers"] = customerList
////                customers.removeObserver(this)
////                checkAndExportData(context, gson, dbData)
////            }
////        }
////    } )
////
////    transactions.observeForever(object : Observer<List<Transaction>> {
////        override fun onChanged(transactionList: List<Transaction>?) {
////            if (transactionList != null) {
////                dbData["transactions"] = transactionList
////                transactions.removeObserver(this)
////                checkAndExportData(context, gson, dbData)
////            }
////
////        }
////
////    } )
////
////    conversionRate.observeForever(object : Observer<List<ConversionRate>> {
////        override fun onChanged(conversionRateList: List<ConversionRate>?) {
////            if (conversionRateList != null) {
////                dbData["conversionRate"] = conversionRateList
////                conversionRate.removeObserver(this)
////                checkAndExportData(context, gson, dbData)
////            }
////
////        }
////
////    } )
////
//////    //Create a data model to hold all the data
//////    val dbData = mapOf(
//////        "customers" to customers,
//////        "transactions" to transactions,
//////        "conversionRate" to conversionRate
//////    )
////
//////    // Convert the data to JSON format
//////    val jsonData =  gson.toJson(dbData)
//////
//////    // Save data to a file
//////    val jsonFile = File(context.getExternalFilesDir(null),"database_backup.json")
//////
//////    try {
//////        FileWriter(jsonFile).use { writer ->
//////            writer.write(jsonData)
//////        }
//////
//////        // Share the JSON File using Intent
//////        val uri = Uri.fromFile(jsonFile)
//////        val intent = Intent(Intent.ACTION_SEND).apply {
//////            type = "application/json"
//////            putExtra(Intent.EXTRA_STREAM, uri)
//////            putExtra(Intent.EXTRA_SUBJECT, "Database Backup")
//////        }
//////
//////        context.startActivity(Intent.createChooser(intent, "Share Database"))
//////
//////    } catch (e: IOException){
//////        e.printStackTrace()
//////    }
////}
////
////fun checkAndExportData(context: Context, gson: Gson, dbData: Map<String, Any>) {
////    if (dbData.containsKey("customers") && dbData.containsKey("transactions") && dbData.containsKey("conversionRate") ){
////        val jsonData = gson.toJson(dbData)
////        val jsonFile = File(context.getExternalFilesDir(null), "database_backup.json")
////
////        try {
////            FileWriter(jsonFile).use { writer ->
////                writer.write(jsonData)
////            }
////
////            val uri = Uri.fromFile(jsonFile)
////            val intent = Intent(Intent.ACTION_SEND).apply {
////                type = "application/json"
////                putExtra(Intent.EXTRA_STREAM, uri)
////                putExtra(Intent.EXTRA_SUBJECT, "Database Backup")
////            }
////            context.startActivity(Intent.createChooser(intent, "Share Database"))
////        } catch (e: IOException) {
////            e.printStackTrace()
////        }
////
////    }
////}
//
//
//package com.brogrammer.rewardme.utils
//
//import android.content.Context
//import android.content.Intent
//import android.net.Uri
//import androidx.core.content.FileProvider
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.lifecycleScope
//import com.brogrammer.rewardme.data.database.AppDatabase
//import com.brogrammer.rewardme.data.model.ConversionRate
//import com.brogrammer.rewardme.data.model.Customer
//import com.brogrammer.rewardme.data.model.Transaction
//import com.google.gson.Gson
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import java.io.File
//import java.io.FileWriter
//import java.io.IOException
//
//suspend fun exportDatabaseToJson(context: Context) {
//    val customerDao = AppDatabase.getDatabase(context).customerDao()
//    val transactionDao = AppDatabase.getDatabase(context).transactionDao()
//    val conversionRateDao = AppDatabase.getDatabase(context).conversionRateDao()
//
//    val customers = withContext(Dispatchers.IO) { customerDao.getAllCustomersList() }
//    val transactions = withContext(Dispatchers.IO) { transactionDao.getAllTransactionsList() }
//    val conversionRate = withContext(Dispatchers.IO) { conversionRateDao.getAllConversionRateList() }
//
//    val gson = Gson()
//
//    val dbData = mapOf(
//        "customers" to customers,
//        "transactions" to transactions,
//        "conversionRate" to conversionRate
//    )
//
//    val jsonData = gson.toJson(dbData)
//    val jsonFile = File(context.getExternalFilesDir(null), "database_backup.json")
//
//    try {
//        FileWriter(jsonFile).use { writer ->
//            writer.write(jsonData)
//        }
//
//        val uri: Uri = FileProvider.getUriForFile(
//            context,
//            "${BuildConfig.APPLICATION_ID}.provider",
//            jsonFile
//        )
//        val intent = Intent(Intent.ACTION_SEND).apply {
//            type = "application/json"
//            putExtra(Intent.EXTRA_STREAM, uri)
//            putExtra(Intent.EXTRA_SUBJECT, "Database Backup")
//        }
//
//        context.startActivity(Intent.createChooser(intent, "Share Database"))
//    } catch (e: IOException) {
//        e.printStackTrace()
//    }
//}

package com.brogrammer.rewardme.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import androidx.core.content.FileProvider
//import com.brogrammer.rewardme.BuildConfig
import com.brogrammer.rewardme.data.database.AppDatabase
import com.brogrammer.rewardme.data.model.ConversionRate
import com.brogrammer.rewardme.data.model.Customer
import com.brogrammer.rewardme.data.model.Transaction
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileWriter
import java.io.IOException

suspend fun exportDatabaseToJson(context: Context) {
    val customerDao = AppDatabase.getDatabase(context).customerDao()
    val transactionDao = AppDatabase.getDatabase(context).transactionDao()
    val conversionRateDao = AppDatabase.getDatabase(context).conversionRateDao()

    val customers = withContext(Dispatchers.IO) { customerDao.getAllCustomersList() }
    val transactions = withContext(Dispatchers.IO) { transactionDao.getAllTransactionsList() }
    val conversionRate = withContext(Dispatchers.IO) { conversionRateDao.getAllConversionRateList() }

    val gson = Gson()

    val dbData = mapOf(
        "customers" to customers,
        "transactions" to transactions,
        "conversionRate" to conversionRate
    )

    val jsonData = gson.toJson(dbData)
    val jsonFile = File(context.getExternalFilesDir(null), "database_backup.json")

    try {
        FileWriter(jsonFile).use { writer ->
            writer.write(jsonData)
        }

        val uri: Uri = FileProvider.getUriForFile(
            context,
//            "${BuildConfig.APPLICATION_ID}.provider",
            "com.brogrammer.rewardme.provider",
            jsonFile
        )

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "application/json"
            putExtra(Intent.EXTRA_STREAM, uri)
            putExtra(Intent.EXTRA_SUBJECT, "Database Backup")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        val appChooser = Intent.createChooser(intent, "Share Database")

        // Grant URI Permissions to all apps that can handle the intent
        val resolveInfoList = context.packageManager.queryIntentActivities(appChooser, PackageManager.MATCH_DEFAULT_ONLY)
        for (resolveInfo in resolveInfoList) {
            val packageName = resolveInfo.activityInfo.packageName
            context.grantUriPermission(packageName, uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

//        context.startActivity(Intent.createChooser(intent, "Share Database"))
        context.startActivity(appChooser)
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

