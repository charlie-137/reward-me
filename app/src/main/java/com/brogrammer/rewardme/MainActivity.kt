package com.brogrammer.rewardme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import com.brogrammer.rewardme.ui.customerdetails.CustomerDetailsScreen
import com.brogrammer.rewardme.ui.customers.AddCustomerScreen
import com.brogrammer.rewardme.ui.customers.CustomersScreen
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.brogrammer.rewardme.ui.components.BottomNavigationBar
import com.brogrammer.rewardme.ui.customerdetails.AddPointsScreen
import com.brogrammer.rewardme.ui.customerdetails.CashOutScreen
import com.brogrammer.rewardme.ui.home.HomeScreen
import com.brogrammer.rewardme.ui.report.ReportScreen
import com.brogrammer.rewardme.ui.settings.ConversionRatesScreen
import com.brogrammer.rewardme.ui.settings.ImportExportScreen
import com.brogrammer.rewardme.ui.settings.SettingsScreen
import com.brogrammer.rewardme.ui.theme.RewardMeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RewardMeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = { BottomNavigationBar(navController = navController) }
                    ) { innerPadding ->
                        Surface (
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            Navigation(navController = navController)
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = "customers") {
        composable("home") { HomeScreen() }
        composable("report") { ReportScreen() }
        composable("customers") { CustomersScreen(navController = navController) }
//        composable("add_customer") { AddCustomerScreen(navController = navController) }


        composable("add_customer?customerId={customerId}", arguments = listOf(
            navArgument("customerId") {
                type = NavType.IntType
                defaultValue = -1
            }
        )) { backStackEntry ->
            val customerId = backStackEntry.arguments?.getInt("customerId") ?: -1
            AddCustomerScreen(navController = navController, customerId = customerId)
        }



        composable("customer_details/{customerId}") { backStackEntry ->
            val customerId = backStackEntry.arguments?.getString("customerId")?.toInt() ?: 0
            CustomerDetailsScreen(navController = navController, customerId = customerId)
        }

        composable("add_points/{customerId}") { backStackEntry ->
            val customerId = backStackEntry.arguments?.getString("customerId")?.toInt() ?: 0
            AddPointsScreen(navController = navController, customerId = customerId)
        }

        composable("cash_out/{customerId}") { backStackEntry ->
            val customerId = backStackEntry.arguments?.getString("customerId")?.toInt() ?: 0
            CashOutScreen(navController = navController, customerId = customerId)
        }

        composable("settings") { SettingsScreen(navController = navController) }
        composable("conversion_rates") { ConversionRatesScreen(navController = navController) }
        composable("import_export_db") { ImportExportScreen(navController = navController) }


    }
}