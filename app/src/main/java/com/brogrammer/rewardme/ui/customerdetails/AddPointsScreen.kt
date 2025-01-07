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
import androidx.compose.material3.ButtonColors
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
fun AddPointsScreen(navController: NavController,
                    customerId: Int,
                    customerViewModel: CustomerViewModel = viewModel(),
                    transactionViewModel: TransactionViewModel = viewModel(),
                    settingsViewModel: SettingsViewModel = viewModel()
) {
    val context = LocalContext.current
    val conversionRate by settingsViewModel.conversionRate.observeAsState()

    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var points by remember {
        mutableStateOf(0f)
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {


        // TextView
        Text(
            text = "ADD POINTS",
            fontSize = 20.sp,
            color = Color(0xFF2389DA),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp, top = 32.dp)
                .align(Alignment.CenterHorizontally)
        )


        NumberBox(
            placeHolder = "Enter Amount",
            text = amount,
            onTextChange = {
                amount = it
                val money = amount.toFloatOrNull() ?: 0f
                points = money * (conversionRate?.moneyToPointsRate ?: 10f)
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

//        TextField(
//            value = amount,
//            onValueChange = {
//                amount = it
//                val money = amount.toFloatOrNull() ?: 0f
//                points = money * (conversionRate?.moneyToPointsRate ?: 10f)
//            },
//            label = { Text("Amount") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 8.dp)
//        )
//
//        TextField(
//            value = description,
//            onValueChange = { description = it },
//            label = { Text("Description") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 8.dp)
//        )

        Text(
            text = "Equivalent Points: $points",
            modifier = Modifier.fillMaxWidth().padding(start = 32.dp,bottom = 16.dp)
        )

        Button(
            onClick = {

                if (amount.isBlank() || description.isBlank())
                {
                    Toast.makeText(
                        context,
                        "Please fill all the fields",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    //val points = (amount.toIntOrNull() ?:0) / 10
                    val points = (amount.toFloatOrNull() ?:0f) * (conversionRate?.moneyToPointsRate ?: 10f)
                    val transaction = Transaction(
                        customerId = customerId,
                        date = Date(),
                        type = "Add",
                        points = points.toInt(),
                        description = description
                    )
                    transactionViewModel.insert(transaction)
                    customerViewModel.updatePoints(customerId, points.toInt())
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
                text = "Add Points",
                color = Color(0xFF707070),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }

    }

}