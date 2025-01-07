package com.brogrammer.rewardme.ui.customers

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.brogrammer.rewardme.data.model.Customer
import com.brogrammer.rewardme.ui.components.NumberBox
import com.brogrammer.rewardme.ui.components.TextBox
import com.brogrammer.rewardme.viewModel.CustomerViewModel

@Composable
fun AddCustomerScreen(
    navController: NavController,
    customerId: Int = -1,
    customerViewModel: CustomerViewModel = viewModel()
) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var contactNumber by remember { mutableStateOf("") }
    var existingCustomer: Customer? by remember { mutableStateOf(null) }

    // Load customer details if editing
    if (customerId != -1) {
        val customer = customerViewModel.getCustomerById(customerId).observeAsState()
//        LaunchedEffect(customerId) {
//            customer.value?.let {
//                name = it.name
//                contactNumber = it.contactNumber
//                existingCustomer = it
//            }
//        }

        customer.value?.let {
            LaunchedEffect(customerId) {
            name = it.name
            contactNumber = it.contactNumber
            existingCustomer = it
            }
        }
    }

    Column (modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {

        // TextView
        Text(
            text = "ENTER CUSTOMER DETAILS",
            fontSize = 20.sp,
            color = Color(0xFF2389DA),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp, top = 32.dp)
                .align(Alignment.CenterHorizontally)
        )

//        TextField(
//            value = name,
//            onValueChange = { name = it },
//            label = { Text("Name") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 8.dp)
//        )
        
        TextBox(
            placeHolder = "Enter Customer Name",
            text = name,
            onTextChange = { name = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        NumberBox(
            placeHolder = "Enter Contact Number",
            text = contactNumber,
            onTextChange = { contactNumber = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

//
//        TextField(
//            value = contactNumber,
//            onValueChange = { contactNumber = it },
//            label = { Text("Contact Number") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 8.dp)
//        )

//        Button(
//            onClick = {
//            val customer = Customer(name = name, contactNumber = contactNumber)
//            customerViewModel.insert(customer)
//            navController.popBackStack()
//            },
//            modifier = Modifier.fillMaxWidth()
//            ) {
//            Text("Add Customer")
//        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {

                if (name.isBlank() || contactNumber.isBlank()){
                    Toast.makeText(
                        context,
                        "Please fill all the fields",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    if (customerId == -1) {
                        // Add new customer
                        val newCustomer = Customer(name = name, contactNumber = contactNumber)
                        customerViewModel.insert(newCustomer)
                    } else {
                        // Update existing customer
//                    val existingCustomer = customerViewModel.getCustomerById(customerId).value
                        existingCustomer?.let {
                            val updatedCustomer = it.copy(
//                            id = customerId,
                                name = name,
                                contactNumber = contactNumber
                            )
                            customerViewModel.update(updatedCustomer)
                        }
//                    val updatedCustomer = Customer(id = customerId, name = name, contactNumber = contactNumber)
//                    customerViewModel.update(updatedCustomer)
                    }
                    navController.popBackStack()
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .size(width = 100.dp, height = 54.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF74CCF4), // Background color
//                contentColor = Color(0xFF707070) // Text color
            )
        ) {
            Text(
                text = if (customerId == -1) "Add Customer" else "Update Customer",
                color = Color(0xFF707070),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }

    }
}