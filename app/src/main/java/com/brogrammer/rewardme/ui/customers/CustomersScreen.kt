package com.brogrammer.rewardme.ui.customers

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.brogrammer.rewardme.viewModel.CustomerViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brogrammer.rewardme.R
import com.brogrammer.rewardme.data.model.Customer

@Composable
fun CustomersScreen(navController: NavController, customerViewModel: CustomerViewModel = viewModel()) {
    val customers = customerViewModel.allCustomers.observeAsState(listOf())
    val searchQuery =  rememberSaveable { mutableStateOf("") }

    val filteredCustomers = if ( searchQuery.value.isEmpty() ) {
        customers.value.sortedBy { it.name }
    } else {
        customers.value.filter { customer ->
            customer.name.contains(searchQuery.value, ignoreCase = true) ||
            customer.contactNumber.contains(searchQuery.value, ignoreCase = true)
        }
    }

    Scaffold(
        topBar = { CustomTopAppBar(searchQuery) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_customer") },
                containerColor = Color(0xFF74CCF4)
            ) {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.baseline_add_24), contentDescription = "Add Customer")
            }
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(filteredCustomers) {customer ->
                CustomerItem(customer = customer, onClick = {
                    navController.navigate("customer_details/${customer.id}")
                })
            }
        }
    }
}

//@Composable
//fun CustomerItem(customer: Customer, onClick: () -> Unit) {
//    Column(modifier = Modifier
//        .fillMaxWidth()
//        .clickable(onClick = onClick)
//        .padding(8.dp)) {
//        Text(text = customer.name)
//        Text(text = "Points: ${customer.points}")
//    }
//}

@Composable
fun CustomerItem(customer: Customer, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//        colors = CardDefaults.cardColors(containerColor = Color(0xFF74CCF4))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.man),
                contentDescription = "Customer Image",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
                contentScale = ContentScale.Crop
                )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = customer.name,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "Contact: ${customer.contactNumber}",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "Points: ${customer.points}",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray,
                        fontSize = 16.sp
                    )
                )
            }

        }
    }
}

@Composable
fun CustomTopAppBar(searchQuery: MutableState<String>) {
    val height = 155.dp
    val cornerRadius = 55.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(bottomStart = cornerRadius, bottomEnd = cornerRadius))
            .background(Color(0xFF74CCF4))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.man),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "SAIF MOBILE",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "The Rewards Management",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            SearchBar(searchQuery)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(searchQuery: MutableState<String>) {
//    val searchQuery = rememberSaveable { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(54.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White)
            .border(1.dp, Color.LightGray, RoundedCornerShape(24.dp)),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.width(8.dp))
            TextField(
                value = searchQuery.value,
                onValueChange = { searchQuery.value = it },
                placeholder = { Text(text = "Search the customer", color = Color(0xFF707070)) },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
//                    focusedContainerColor = Color.Transparent,
//                    unfocusedContainerColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth()
//                    .background(Color.Transparent)
            )
        }
    }
}