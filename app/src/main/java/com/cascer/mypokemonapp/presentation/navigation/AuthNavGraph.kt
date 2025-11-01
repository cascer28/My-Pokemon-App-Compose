package com.cascer.mypokemonapp.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cascer.mypokemonapp.presentation.screen.login.LoginScreen
import com.cascer.mypokemonapp.presentation.screen.register.RegisterScreen
import com.cascer.mypokemonapp.presentation.screen.splash.SplashScreen

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    composable(Routes.SPLASH) { SplashScreen(navController) }
    composable(Routes.LOGIN) { LoginScreen(navController) }
    composable(Routes.REGISTER) { RegisterScreen(navController) }
}