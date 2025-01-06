package com.brogrammer.rewardme.ui.settings

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
import com.brogrammer.rewardme.data.model.ConversionRate
import com.brogrammer.rewardme.ui.components.NumberBox
import com.brogrammer.rewardme.ui.components.TextBox
import com.brogrammer.rewardme.viewModel.SettingsViewModel

@Composable
fun ConversionRatesScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel = viewModel()
) {
    val context = LocalContext.current
    val conversionRate by settingsViewModel.conversionRate.observeAsState()

    var moneyForPoints by remember { mutableStateOf("") }
    var pointsForMoney by remember { mutableStateOf("") }
    var pointsForMoneyToPoints by remember { mutableStateOf("") }
    var moneyForPointsToMoney by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // TextView
        Text(
            text = "CONVERSION RATES",
            fontSize = 20.sp,
            color = Color(0xFF2389DA),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp, top = 32.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = "Money to Points Conversion",
            modifier = Modifier.padding(start = 32.dp, bottom = 8.dp)
        )


        NumberBox(
            placeHolder = "Money",
            text = moneyForPoints,
            onTextChange = { moneyForPoints = it }
        )

        Spacer(modifier = Modifier.height(8.dp))


        NumberBox(
            placeHolder = "Points",
            text = pointsForMoneyToPoints,
            onTextChange = { pointsForMoneyToPoints = it }
        )

        Spacer(modifier = Modifier.height(8.dp))
        Spacer(modifier = Modifier.height(8.dp))

//
//        TextField(
//            value = moneyForPoints,
//            onValueChange = { moneyForPoints = it },
//            label = { Text("Money") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 8.dp)
//
//        )
//
//        TextField(
//            value = pointsForMoneyToPoints,
//            onValueChange = { pointsForMoneyToPoints = it },
//            label = { Text("Points") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 16.dp)
//
//        )


        Text(
            text = "Points to Money Conversion",
            modifier = Modifier.padding(start = 32.dp, bottom = 8.dp)
        )


        NumberBox(
            placeHolder = "Points",
            text = pointsForMoney,
            onTextChange = { pointsForMoney = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        NumberBox(
            placeHolder = "Money",
            text = moneyForPointsToMoney,
            onTextChange = { moneyForPointsToMoney = it }
        )


//        TextField(
//            value = pointsForMoney,
//            onValueChange = { pointsForMoney = it },
//            label = { Text("Points") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 8.dp)
//
//        )
//
//        TextField(
//            value = moneyForPointsToMoney,
//            onValueChange = { moneyForPointsToMoney = it },
//            label = { Text("Money") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 16.dp)
//
//        )

        Spacer(modifier = Modifier.height(8.dp))
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {

                if (moneyForPoints.isBlank() || pointsForMoney.isBlank() || pointsForMoneyToPoints.isBlank() || moneyForPointsToMoney.isBlank()){
                    Toast.makeText(
                        context,
                        "Please fill all the fields",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val moneyToPointsRate = (pointsForMoneyToPoints.toFloatOrNull() ?: 0f) / (moneyForPoints.toFloatOrNull() ?: 0f)
                    val pointsToMoneyRate = (moneyForPointsToMoney.toFloatOrNull() ?: 0f) / (pointsForMoney.toFloatOrNull() ?: 0f)

                    val newConversionRate = ConversionRate(
                        moneyToPointsRate = moneyToPointsRate,
                        pointsToMoneyRate = pointsToMoneyRate
                    )

                    if (conversionRate == null) {
                        settingsViewModel.insert(newConversionRate)
                    } else {
                        settingsViewModel.update(newConversionRate)
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
                text = "Save Rates",
                color = Color(0xFF707070),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
                )
        }


    }
}