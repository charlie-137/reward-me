package com.brogrammer.rewardme.ui.customers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.brogrammer.rewardme.viewModel.CustomerViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.brogrammer.rewardme.R
import com.brogrammer.rewardme.data.model.Customer

@Composable
fun CustomersScreen(navController: NavController, customerViewModel: CustomerViewModel = viewModel()) {
    val customers = customerViewModel.allCustomers.observeAsState(listOf())
    
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("add_customer") }) {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.baseline_add_24), contentDescription = "Add Customer")
            }
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(customers.value) {customer ->
                CustomerItem(customer = customer, onClick = {
                    navController.navigate("customer_details/${customer.id}")
                })
            }
        }
    }
}

@Composable
fun CustomerItem(customer: Customer, onClick: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = onClick)
        .padding(8.dp)) {
        Text(text = customer.name)
        Text(text = "Points: ${customer.points}")
    }
}