package com.brogrammer.rewardme.ui.customerdetails

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
import com.brogrammer.rewardme.data.model.Transaction
import com.brogrammer.rewardme.ui.components.NumberBox
import com.brogrammer.rewardme.ui.components.TextBox
import com.brogrammer.rewardme.viewModel.CustomerViewModel
import com.brogrammer.rewardme.viewModel.SettingsViewModel
import com.brogrammer.rewardme.viewModel.TransactionViewModel
import java.util.Date

@Composable
fun CashOutScreen(
    navController: NavController,
    customerId: Int,
    customerViewModel: CustomerViewModel = viewModel(),
    transactionViewModel: TransactionViewModel = viewModel(),
    settingsViewModel: SettingsViewModel = viewModel()
) {
    val context = LocalContext.current
    val conversionRate by settingsViewModel.conversionRate.observeAsState()

   var pointsToRedeem by remember { mutableStateOf("") }
   var description by remember { mutableStateOf("") }
    var money by remember {
        mutableStateOf(0f)
    }

    val balancePoints by customerViewModel.getPointsByCustomerId(customerId).observeAsState(0)

   Column(
       modifier = Modifier
           .fillMaxSize()
           .padding(16.dp)
   ) {

//       TextField(
//           value = pointsToRedeem,
//           onValueChange = {
//               pointsToRedeem = it
//               val points = pointsToRedeem.toIntOrNull() ?: 0
//               money = points * (conversionRate?.pointsToMoneyRate ?: 0.1f)
//            },
//           label = { Text("Points to Redeem") },
//           modifier = Modifier
//               .fillMaxWidth()
//               .padding(bottom = 8.dp)
//       )

       // TextView
       Text(
           text = "REDEEM POINTS",
           fontSize = 20.sp,
           color = Color(0xFF2389DA),
           fontWeight = FontWeight.Bold,
           modifier = Modifier
               .padding(bottom = 16.dp, top = 32.dp)
               .align(Alignment.CenterHorizontally)
       )


       NumberBox(
           placeHolder = "Enter Points To Redeem",
           text = pointsToRedeem,
           onTextChange = {
               pointsToRedeem = it
               val points = pointsToRedeem.toIntOrNull() ?: 0
               money = points * (conversionRate?.pointsToMoneyRate ?: 0.1f)
           },
           modifier = Modifier.fillMaxWidth()
       )

       Spacer(modifier = Modifier.height(8.dp))

       TextBox(
           placeHolder = "Enter Description",
           text = description,
           onTextChange = { description = it }
       )

       Spacer(modifier = Modifier.height(8.dp))
       Spacer(modifier = Modifier.height(8.dp))

//       TextField(
//           value = description,
//           onValueChange = { description = it },
//           label = { Text("Description") },
//           modifier = Modifier
//               .fillMaxWidth()
//               .padding(bottom = 8.dp)
//       )

       Text(
           text = "Equivalent Money: $money",
           modifier = Modifier
               .fillMaxWidth()
               .padding(start = 32.dp, bottom = 16.dp)
       )


       Button(
           onClick = {
               if (pointsToRedeem.isBlank() || description.isBlank()){
                   Toast.makeText(
                       context,
                       "Please fill all the fields",
                       Toast.LENGTH_SHORT
                   ).show()
               } else {
                   val points = pointsToRedeem.toIntOrNull() ?: 0
//                   val balancePoints = customerViewModel.getPointsByCustomerId(customerId)
                   if(balancePoints < points) {
                       Toast.makeText(
                           context,
                           "Not Sufficient Points to Redeem",
                           Toast.LENGTH_SHORT
                       ).show()
                   } else {
                       //               money = points * (conversionRate?.pointsToMoneyRate ?: 0.1f)
                       val transaction = Transaction(
                           customerId = customerId,
                           date = Date(),
                           type = "Redeem",
                           points = -points,
                           description = description
                       )
                       transactionViewModel.insert(transaction)
                       customerViewModel.updatePoints(customerId, -points)
                       navController.popBackStack()
                   }

               }
           },
           modifier = Modifier
               .fillMaxWidth()
               .size(width = 100.dp, height = 54.dp),
           colors = ButtonDefaults.buttonColors(
               containerColor = Color(0xFF74CCF4), // Background color
//               contentColor = Color(0xFF707070) // Text color
           )
           ) {
           Text(
               text = "Cash Out",
               color = Color(0xFF707070),
               fontWeight = FontWeight.SemiBold,
               fontSize = 16.sp
           )
       }

   }
}