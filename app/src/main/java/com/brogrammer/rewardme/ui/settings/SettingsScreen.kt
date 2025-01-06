package com.brogrammer.rewardme.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SettingsScreen(navController: NavController){
//    Text("Settings Screen")
    // TextView
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .padding(16.dp)
    ) {
        Text(
            text = "SETTINGS",
            fontSize = 20.sp,
            color = Color(0xFF2389DA),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp, top = 32.dp)
            .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 96.dp, bottom = 8.dp)
                .clickable {
                    navController.navigate("conversion_rates")
                },
            shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp, topEnd = 0.dp, bottomEnd = 0.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF74CCF4))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
//                Text(text = "Conversion Rates")
//                Text(text = "Set Conversion rates for points to money and money to points")
                Text(
                    text = "Conversion Rate",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "Set the point conversion rates",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                )
            }
        }
    }
}