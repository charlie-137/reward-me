package com.brogrammer.rewardme.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.brogrammer.rewardme.data.model.ConversionRate
import com.brogrammer.rewardme.viewModel.SettingsViewModel

@Composable
fun ConversionRatesScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel = viewModel()
) {
    val conversionRate by settingsViewModel.conversionRate.observeAsState()

//    var moneyToPointsRate by remember {
//        mutableStateOf(conversionRate?.moneyToPointsRate?.toString() ?: "10")
//    }
//
//    var pointsToMoneyRate by remember {
//        mutableStateOf(conversionRate?.pointsToMoneyRate?.toString() ?: "0.1")
//    }

    var moneyForPoints by remember { mutableStateOf("") }
    var pointsForMoney by remember { mutableStateOf("") }
    var pointsForMoneyToPoints by remember { mutableStateOf("") }
    var moneyForPointsToMoney by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Money to Points Conversion",
            modifier = Modifier.padding(bottom = 8.dp)
        )
        TextField(
            value = moneyForPoints,
            onValueChange = { moneyForPoints = it },
            label = { Text("Money") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)

        )

        TextField(
            value = pointsForMoneyToPoints,
            onValueChange = { pointsForMoneyToPoints = it },
            label = { Text("Points") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)

        )


        Text(
            text = "Points to Money Conversion",
            modifier = Modifier.padding(bottom = 8.dp)
        )
        TextField(
            value = pointsForMoney,
            onValueChange = { pointsForMoney = it },
            label = { Text("Points") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)

        )

        TextField(
            value = moneyForPointsToMoney,
            onValueChange = { moneyForPointsToMoney = it },
            label = { Text("Money") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)

        )

        Button(
            onClick = {
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

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Rates")
        }


    }

//    Column (
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//
//        TextField(
//            value = moneyToPointsRate,
//            onValueChange = { moneyToPointsRate = it },
//            label = { Text("Money to Points Rate") },
//            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
//
//        )
//
//        TextField(
//            value = pointsToMoneyRate,
//            onValueChange = { pointsToMoneyRate = it },
//            label = { Text("Points to Money Rate") },
//            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
//
//        )
//    }
}