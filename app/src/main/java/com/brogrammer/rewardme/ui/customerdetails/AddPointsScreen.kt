package com.brogrammer.rewardme.ui.customerdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.brogrammer.rewardme.data.model.Transaction
import com.brogrammer.rewardme.viewModel.CustomerViewModel
import com.brogrammer.rewardme.viewModel.TransactionViewModel
import java.util.Date

@Composable
fun AddPointsScreen(navController: NavController,
                    customerId: Int,
                    customerViewModel: CustomerViewModel = viewModel(),
                    transactionViewModel: TransactionViewModel = viewModel()
) {
    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        Button(
            onClick = {
            val points = (amount.toIntOrNull() ?:0) / 10
                val transaction = Transaction(
                    customerId = customerId,
                    date = Date(),
                    type = "Add",
                    points = points,
                    description = description
                )
                transactionViewModel.insert(transaction)
                customerViewModel.updatePoints(customerId, points)
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Points")
        }

    }

}