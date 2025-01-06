package com.brogrammer.rewardme.ui.customerdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.brogrammer.rewardme.R
import com.brogrammer.rewardme.data.model.Transaction
import com.brogrammer.rewardme.utils.formatDate
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


            // Customer Details Section
            Text(
                text = "Customer Details:",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
//                    Text(
//                        text = "Customer Details",
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Bold
//                    )
                    Text(
                        text = "Name: ${it.name}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "Contact: ${it.contactNumber}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
//                    Text(
//                        text = "Points: ${it.points}",
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Medium
//                    )
                    Button(
                        onClick = { navController.navigate("add_customer?customerId=$customerId") },
                        modifier = Modifier
                            .align(Alignment.End)
                            .size(width = 100.dp, height = 36.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00B0FF)) // Light Blue
                    ) {
                        Text(
                            text = "Edit",
                            fontSize = 12.sp
                        )
                    }

//                    Button(
//                        onClick = { navController.navigate("add_points/$customerId") },
//                        modifier = Modifier.size(width = 100.dp, height = 36.dp),
//                        shape = RoundedCornerShape(8.dp)
//                    ) {
//                        Text(
//                            text = "Add Points",
//                            fontSize = 12.sp
//                        )
//                    }

                }
            }





//            Text(text = "Name: ${it.name}")
//            Text(text = "Contact: ${it.contactNumber}")
//            Text(text = "Points: ${it.points}")
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Button(onClick = {
//                navController.navigate("add_customer?customerId=$customerId")
//            }) {
//                Text("Edit Details")
//            }



            // Customer Points Section
            Text(
                text = "Points:",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth(),
//                    .padding(vertical = 12.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Internal Card spanning the space till buttons
                    Card(
                        modifier = Modifier
                            .weight(1f) // Occupies all space till the buttons
                            .padding(end = 16.dp), // Spacing between internal card and buttons
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF74CCF4)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(14.dp) // Internal padding for the content
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Points",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = "${it.points}",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.DarkGray
                            )
                        }
                    }

                    // Buttons on the right
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier
                            .wrapContentWidth() // Ensure buttons take only the necessary space
                    ) {
                        Button(
                            onClick = { navController.navigate("add_points/$customerId") },
                            modifier = Modifier.size(width = 100.dp, height = 36.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)) // Green
                        ) {
                            Text(
                                text = "Add",
                                fontSize = 12.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = { navController.navigate("cash_out/$customerId") },
                            modifier = Modifier.size(width = 100.dp, height = 36.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                        ) {
                            Text(
                                text = "Redeem",
                                fontSize = 12.sp,
                                color = Color.White
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            // Transactions Section
            Text(
                text = "Transactions:",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            val sortedTransactions = transactions.value.sortedByDescending { transaction ->
                transaction.date
            }

            if (transactions.value.isEmpty()) {
                Text(
                    text = "No transactions available.",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(sortedTransactions) { transaction ->
                        TransactionCard(transaction = transaction)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

            }

        }
    }
}


@Composable
fun TransactionCard(transaction: Transaction) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
//                    text = transaction.date.toString(),
                    text = formatDate(transaction.date),
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Text(
                    text = transaction.type,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (transaction.type == "Add") Color(0xFF4CAF50) else Color(0xFFF44336)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = transaction.description,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "${transaction.points} points",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
