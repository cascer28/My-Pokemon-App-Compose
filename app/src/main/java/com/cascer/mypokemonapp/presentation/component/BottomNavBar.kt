package com.cascer.mypokemonapp.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cascer.mypokemonapp.R
import com.cascer.mypokemonapp.presentation.navigation.Routes

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem(Routes.HOME, stringResource(R.string.nav_home), Icons.Default.Home),
        BottomNavItem(Routes.PROFILE, stringResource(R.string.nav_profile), Icons.Default.Person)
    )

    NavigationBar {
        val currentDestination = navController
            .currentBackStackEntryAsState().value?.destination

        items.forEach { item ->
            NavigationBarItem(
                selected = currentDestination?.route == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) { inclusive = true }
                        launchSingleTop = true
                        restoreState = false
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                alwaysShowLabel = false
            )
        }
    }
}

data class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
)