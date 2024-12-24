package com.brogrammer.rewardme.ui.customerdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.brogrammer.rewardme.viewModel.CustomerViewModel
import com.brogrammer.rewardme.viewModel.TransactionViewModel

@Composable
fun CustomerDetailsScreen(
    navController: NavController,
    customerId: Int,
    customerViewModel: CustomerViewModel = viewModel(),
    transactionViewModel: TransactionViewModel = viewModel()
) {
    val customer = customerViewModel.getCustomerById(customerId).observeAsState()
    val transactions = transactionViewModel.getTransactionsByCustomerId(customerId).observeAsState(listOf())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        customer.value?.let {
            Text(text = "Name: ${it.name}")
            Text(text = "Contact: ${it.contactNumber}")
            Text(text = "Points: ${it.points}")

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                navController.navigate("add_points/$customerId")
            }) {
                Text("Add Points")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                navController.navigate("cash_out/$customerId")
            }) {
                Text("Cash Out")
            }

            Spacer(modifier = Modifier.height(16.dp))
            
            Text(text = "Transactions:")
            transactions.value.forEach { transaction -> 
                Text(text = "${transaction.date}: ${transaction.type} ${transaction.points} points - ${transaction.description}")
            }

        }
    }
}