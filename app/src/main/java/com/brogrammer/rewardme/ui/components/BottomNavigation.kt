package com.brogrammer.rewardme.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.brogrammer.rewardme.R
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Customers,
        BottomNavItem.Report,
        BottomNavItem.Settings
    )

    NavigationBar {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = ImageVector.vectorResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )

        }
    }
}

sealed class BottomNavItem(var title: String, var icon: Int, var route: String) {
    object Home : BottomNavItem("Home", R.drawable.baseline_home_24, "home")
    object Customers : BottomNavItem("Customers", R.drawable.baseline_people_24, "customers")
    object Report : BottomNavItem("Report", R.drawable.baseline_library_books_24, "report")
    object Settings : BottomNavItem("Settings", R.drawable.baseline_settings_24, "settings")
}