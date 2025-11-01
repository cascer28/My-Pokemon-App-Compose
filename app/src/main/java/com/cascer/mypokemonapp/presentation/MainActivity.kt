package com.cascer.mypokemonapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cascer.mypokemonapp.presentation.component.BottomNavBar
import com.cascer.mypokemonapp.presentation.navigation.Routes
import com.cascer.mypokemonapp.presentation.navigation.authNavGraph
import com.cascer.mypokemonapp.presentation.navigation.mainNavGraph
import com.cascer.mypokemonapp.presentation.theme.MyPokemonAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyPokemonAppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val noBottomBarRoutes = listOf(
        Routes.SPLASH,
        Routes.LOGIN,
        Routes.REGISTER,
        Routes.DETAIL
    )

    val currentDestination = navController
        .currentBackStackEntryAsState().value?.destination
    val currentRoute = currentDestination?.route

    Scaffold(
        bottomBar = {
            if (noBottomBarRoutes.none { currentRoute?.startsWith(it.substringBefore("/{")) == true }) {
                BottomNavBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.SPLASH,
            modifier = Modifier.padding(innerPadding)
        ) {
            authNavGraph(navController)
            mainNavGraph(navController)
        }
    }
}